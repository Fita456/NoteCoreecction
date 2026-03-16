package com.exam.correction.service;

import com.exam.correction.entity.*;
import com.exam.correction.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteFinalService {
    
    @Autowired
    private NoteFinalRepository noteFinalRepository;
    
    @Autowired
    private NotesRepository notesRepository;
    
    @Autowired
    private ParametreRepository parametreRepository;
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private MatiereRepository matiereRepository;
    
    public List<NoteFinal> getAllNotesFinal() {
        return noteFinalRepository.findAll();
    }
    
    public Optional<NoteFinal> getNoteFinalById(Long id) {
        return noteFinalRepository.findById(id);
    }
    
    public List<NoteFinal> getNotesFinalByEtudiant(Long etudiantId) {
        return noteFinalRepository.findByEtudiantId(etudiantId);
    }
    
    public List<NoteFinal> getNotesFinalByMatiere(Long matiereId) {
        return noteFinalRepository.findByMatiereId(matiereId);
    }
    
    public void deleteNoteFinal(Long id) {
        noteFinalRepository.deleteById(id);
    }
    
    @Transactional
    public NoteFinal calculerEtEnregistrerNoteFinal(Long etudiantId, Long matiereId) {
        List<Notes> notes = notesRepository.findByEtudiantIdAndMatiereId(etudiantId, matiereId);
        
        if (notes.isEmpty()) {
            throw new RuntimeException("Aucune note trouvée pour cet étudiant dans cette matière");
        }
        
        if (notes.size() < 2) {
            throw new RuntimeException("Il faut au moins 2 notes pour calculer des différences");
        }
        
        List<Parametre> parametres = parametreRepository.findAllByMatiereId(matiereId);
        
        if (parametres.isEmpty()) {
            throw new RuntimeException("Aucun paramètre défini pour cette matière");
        }
        
        double noteFinalCalculee = calculerNoteFinalAvecRegles(notes, parametres);
        
        Parametre parametrePrincipal = parametres.get(0);
        
        Optional<NoteFinal> noteFinalExistante = noteFinalRepository
            .findByEtudiantIdAndMatiereIdAndParametreId(etudiantId, matiereId, parametrePrincipal.getId());
        
        NoteFinal noteFinal;
        if (noteFinalExistante.isPresent()) {
            noteFinal = noteFinalExistante.get();
            noteFinal.setNoteFinal(noteFinalCalculee);
            noteFinal.setDateEntre(LocalDateTime.now());
        } else {
            noteFinal = new NoteFinal();
            noteFinal.setEtudiant(etudiantRepository.findById(etudiantId).orElseThrow());
            noteFinal.setMatiere(matiereRepository.findById(matiereId).orElseThrow());
            noteFinal.setParametre(parametrePrincipal);
            noteFinal.setNoteFinal(noteFinalCalculee);
            noteFinal.setDateEntre(LocalDateTime.now());
        }
        
        return noteFinalRepository.save(noteFinal);
    }
    
    /**
     * Calculer la note finale SANS ARRONDI
     */
    private double calculerNoteFinalAvecRegles(List<Notes> notes, List<Parametre> parametres) {
        List<Double> valeurs = notes.stream()
            .map(Notes::getValeur)
            .sorted()
            .collect(Collectors.toList());
        
        List<Double> differences = calculerToutesDifferences(valeurs);
        
        List<Double> notesCandidates = new ArrayList<>();
        StringBuilder erreursRegles = new StringBuilder();
        
        for (Parametre parametre : parametres) {
            double valeurDifference = appliquerResolution(differences, parametre.getResolution());
            
            // PLUS D'ARRONDI - Vérifier directement avec la valeur calculée
            boolean respecte = verifierCondition(valeurDifference, parametre);
            
            if (respecte) {
                double noteCandidate = appliquerResolution(valeurs, parametre.getResolution());
                notesCandidates.add(noteCandidate);
            } else {
                if (erreursRegles.length() > 0) {
                    erreursRegles.append(" ET ");
                }
                erreursRegles.append(String.format(
                    "%s(différences)=%.2f %s %.2f",
                    parametre.getResolution().getRef(),
                    valeurDifference,
                    getSymboleComparateur(parametre.getComparateur()),
                    parametre.getSeuil()
                ));
            }
        }
        
        if (notesCandidates.isEmpty()) {
            throw new RuntimeException("Aucune règle respectée : " + erreursRegles.toString());
        }
        
        return notesCandidates.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    }
    
    /**
     * Calculer toutes les différences entre chaque paire de notes
     */
    private List<Double> calculerToutesDifferences(List<Double> valeurs) {
        List<Double> differences = new ArrayList<>();
        
        for (int i = 0; i < valeurs.size(); i++) {
            for (int j = i + 1; j < valeurs.size(); j++) {
                double diff = Math.abs(valeurs.get(i) - valeurs.get(j));
                differences.add(diff);
            }
        }
        
        return differences;
    }
    
    /**
     * Appliquer la résolution (MIN, MAX, AVERAGE) sur une liste de valeurs
     */
    private double appliquerResolution(List<Double> valeurs, Resolution resolution) {
        switch (resolution.getRef()) {
            case MIN:
                return valeurs.stream().min(Double::compare).orElse(0.0);
            case MAX:
                return valeurs.stream().max(Double::compare).orElse(0.0);
            case AVERAGE:
                return valeurs.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            default:
                throw new RuntimeException("Type de résolution non supporté");
        }
    }
    
    /**
     * Vérifier si la condition du comparateur est respectée
     */
    private boolean verifierCondition(double valeur, Parametre parametre) {
        double seuil = parametre.getSeuil();
        
        switch (parametre.getComparateur().getRef()) {
            case INFERIEUR:
                return valeur < seuil;
            case SUPERIEUR:
                return valeur > seuil;
            case INFERIEUR_OU_EGAL:
                return valeur <= seuil;
            case SUPERIEUR_OU_EGAL:
                return valeur >= seuil;
            default:
                return false;
        }
    }
    
    private String getSymboleComparateur(Comparateur comparateur) {
        switch (comparateur.getRef()) {
            case INFERIEUR: return "<";
            case SUPERIEUR: return ">";
            case INFERIEUR_OU_EGAL: return "<=";
            case SUPERIEUR_OU_EGAL: return ">=";
            default: return "";
        }
    }
    
    private String getDescriptionResolution(Resolution resolution) {
        switch (resolution.getRef()) {
            case MIN: return "minimum";
            case MAX: return "maximum";
            case AVERAGE: return "moyenne";
            default: return "";
        }
    }
    
    /**
     * Obtenir les détails de calcul pour l'affichage
     */
    public Map<String, Object> getDetailsCalcul(Long etudiantId, Long matiereId) {
        List<Notes> notes = notesRepository.findByEtudiantIdAndMatiereId(etudiantId, matiereId);
        List<Parametre> parametres = parametreRepository.findAllByMatiereId(matiereId);
        
        if (notes.isEmpty()) {
            throw new RuntimeException("Aucune note trouvée");
        }
        if (parametres.isEmpty()) {
            throw new RuntimeException("Aucun paramètre défini");
        }
        
        List<Double> valeurs = notes.stream()
            .map(Notes::getValeur)
            .sorted()
            .collect(Collectors.toList());
        
        double minNotes = valeurs.stream().min(Double::compare).orElse(0.0);
        double maxNotes = valeurs.stream().max(Double::compare).orElse(0.0);
        double avgNotes = valeurs.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        
        List<Double> differences = calculerToutesDifferences(valeurs);
        
        double diffMin = differences.stream().min(Double::compare).orElse(0.0);
        double diffMax = differences.stream().max(Double::compare).orElse(0.0);
        double diffMoyenne = differences.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        
        List<Double> seuils = parametres.stream()
            .map(Parametre::getSeuil)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        
        List<String> differencesDetails = new ArrayList<>();
        for (int i = 0; i < valeurs.size(); i++) {
            for (int j = i + 1; j < valeurs.size(); j++) {
                double diff = Math.abs(valeurs.get(i) - valeurs.get(j));
                differencesDetails.add(String.format("|%.2f - %.2f| = %.2f", 
                    valeurs.get(i), valeurs.get(j), diff));
            }
        }
        
        List<Map<String, Object>> validationsParametres = new ArrayList<>();
        List<Double> notesCandidates = new ArrayList<>();
        
        for (Parametre parametre : parametres) {
            Map<String, Object> validationParam = new HashMap<>();
            
            double valeurDifference = appliquerResolution(differences, parametre.getResolution());
            
            // PLUS D'ARRONDI
            boolean respecte = verifierCondition(valeurDifference, parametre);
            double noteCandidate = appliquerResolution(valeurs, parametre.getResolution());
            
            validationParam.put("parametre", parametre);
            validationParam.put("valeurDifference", valeurDifference);
            validationParam.put("noteCandidate", noteCandidate);
            validationParam.put("respecte", respecte);
            validationParam.put("symbole", getSymboleComparateur(parametre.getComparateur()));
            validationParam.put("description", getDescriptionResolution(parametre.getResolution()));
            
            if (respecte) {
                notesCandidates.add(noteCandidate);
            }
            
            validationsParametres.add(validationParam);
        }
        
        Double noteFinalCalculee = null;
        if (!notesCandidates.isEmpty()) {
            noteFinalCalculee = notesCandidates.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        }
        
        Map<String, Object> details = new HashMap<>();
        details.put("notes", notes);
        details.put("parametres", parametres);
        details.put("validationsParametres", validationsParametres);
        details.put("valeurs", valeurs);
        details.put("minNotes", minNotes);
        details.put("maxNotes", maxNotes);
        details.put("avgNotes", avgNotes);
        details.put("differences", differences);
        details.put("differencesDetails", differencesDetails);
        details.put("diffMin", diffMin);
        details.put("diffMax", diffMax);
        details.put("diffMoyenne", diffMoyenne);
        details.put("seuils", seuils);
        details.put("notesCandidates", notesCandidates);
        details.put("noteFinalCalculee", noteFinalCalculee);
        details.put("auMoinsUneRegleRespectee", !notesCandidates.isEmpty());
        
        return details;
    }
}