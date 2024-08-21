import java.time.LocalDate;
import java.util.Objects;

public class Depense {
    private String description;
    private Double montant;
    private categorieDepense categorie;
    private LocalDate date;

    public Depense(categorieDepense categorie, LocalDate date, String description, Double montant) {
        this.categorie = categorie;
        this.date = date;
        this.description = description;
        this.montant = montant;
    }

    public categorieDepense getCategorie() {
        return categorie;
    }

    public void setCategorie(categorieDepense categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depense depense = (Depense) o;
        return Objects.equals(description, depense.description) && Objects.equals(montant, depense.montant) && categorie == depense.categorie && Objects.equals(date, depense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, montant, categorie, date);
    }
}
