import java.util.*;

public class User {
    private String nom;
    private Double budgetMensuel;
    private List<Depense> depense;

    public User(Double budgetMensuel, List<Depense> depense, String nom) {
        this.budgetMensuel = budgetMensuel;
        this.depense = depense;
        this.nom = nom;
    }

    public Double getBudgetMensuel() {
        return budgetMensuel;
    }

    public void setBudgetMensuel(Double budgetMensuel) {
        this.budgetMensuel = budgetMensuel;
    }

    public List<Depense> getDepenseByDate() {
        List<Depense> sortedList = new ArrayList<>(depense);
        sortedList.sort(Comparator.comparing(Depense::getDate));
        return sortedList;
    }

    public List<Depense> getDepenseByCategory() {
        List<Depense> sortedList = new ArrayList<>(depense);
        sortedList.sort(Comparator.comparing(Depense::getCategorie));
        return sortedList;
    }

    public void setDepense(List<Depense> depense) {
        this.depense = depense;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addDepense (Depense d) {
        if (d.getMontant() > 0){
            depense.add(d);
        }
        else {
            System.out.println("Erreur, montant negatif");
        }
    }

    public List<Depense> getDepenseByCategory (categorieDepense c){
        List<Depense> listDepense = new ArrayList<>();
        for (Depense d : depense) {
            if (d.getCategorie() == c){
                listDepense.add(d);
            }
        }
        return listDepense;
    }

    public double getDepenseDuMois() {
        double sum = 0;
        for (int i=0; i<depense.size(); i++){
            sum += depense.get(i).getMontant();
        }
        return sum;
    }

    public double getBudgetRestant () {
        if (budgetMensuel - this.getDepenseDuMois() < 0) {
            System.out.println("Budget depasse");
        }
        return budgetMensuel - this.getDepenseDuMois();
    }

    /**public List<categorieDepense> getTopCategories () {
        return ;
    }*/

    public HashMap<categorieDepense, Double> averageSpendingPerCategory () {
        HashMap<categorieDepense, Double> top3 = new HashMap<categorieDepense, Double>();
        List<Depense> sortedList = this.getDepenseByCategory();
        categorieDepense c;
        double sum = 0;
        for (int i=0; i<sortedList.size(); i++) {
            if (i==0) {
                sum += sortedList.get(i).getMontant();
            }
            else if (i > 0 && i < sortedList.size()-1) {
                if (sortedList.get(i).getCategorie() == sortedList.get(i-1).getCategorie()){
                    sum += sortedList.get(i).getMontant();
                } else if (sortedList.get(i).getCategorie() != sortedList.get(i-1).getCategorie()){
                    top3.put(sortedList.get(i-1).getCategorie(), sum);
                    sum = 0;
                    sum += sortedList.get(i).getMontant();
                }
            } else if (i == sortedList.size()-1) {
                if (sortedList.get(i).getCategorie() == sortedList.get(i-1).getCategorie()){
                    sum += sortedList.get(i).getMontant();
                } else if (sortedList.get(i).getCategorie() != sortedList.get(i-1).getCategorie()){
                    sum = 0;
                    sum += sortedList.get(i).getMontant();
                    top3.put(sortedList.get(i).getCategorie(), sum);
                }
            }
        }
        return top3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nom, user.nom) && Objects.equals(budgetMensuel, user.budgetMensuel) && Objects.equals(depense, user.depense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, budgetMensuel, depense);
    }
}
