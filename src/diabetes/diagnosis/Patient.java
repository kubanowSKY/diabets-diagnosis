package diabetes.diagnosis;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * @author kubanowsky
 *
 */
public class Patient {
	
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty gender;
	private StringProperty peselNumber;
	private StringProperty insurance;
	private BooleanProperty examined;
	private Examination examination;

	/**
	 * Konstruktor
	 * 
	 * @param imię
	 * @param nazwisko
	 * @param płeć
	 * @param pesel
	 * @param ubezpieczenie
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
	 */
	public Patient(String firstName, String lastName, String gender,
			String peselNumber, String insurance) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.gender = new SimpleStringProperty(gender);
		this.peselNumber = new SimpleStringProperty(peselNumber);
		this.insurance = new SimpleStringProperty(insurance);
		this.examined = new SimpleBooleanProperty(false);
	}
	
	/**
	 * @return imię
	 */
	public String getFirstName() {
		return firstName.get();
	}
	
	/**
	 * @param imię
	 */
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	/**
	 * @return uchwyt na pole imię
	 */
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	/**
	 * @return nazwisko
	 */
	public String getLastName() {
		return firstName.get();
	}
	
	/**
	 * @param nazwisko
	 */
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	/**
	 * @return uchwyt na pole nazwisko
	 */
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	/**
	 * @return płeć
	 */
	public String getGender() {
		return gender.get();
	}
	
	/**
	 * @param płeć
	 */
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	
	/**
	 * @return uchwyt na pole płeć
	 */
	public StringProperty genderProperty() {
		return gender;
	}
	
	/**
	 * @return pesel
	 */
	public String getPeselNumber() {
		return peselNumber.get();
	}
	
	/**
	 * @param pesel
	 */
	public void setPeselNumber(String peselNumber) {
		this.peselNumber.set(peselNumber);
	}
	
	/**
	 * @return uchwyt na pole płeć
	 */
	public StringProperty peselNumberProperty() {
		return peselNumber;
	}
	
	/**
	 * @return ubezpieczenie
	 */
	public String getInsurance() {
		return insurance.get();
	}
	
	/**
	 * @param imię
	 */
	public void setInsurance(String insurance) {
		this.insurance.set(insurance);
	}
	
	/**
	 * @return uchwyt na pole imię
	 */
	public StringProperty insuranceProperty() {
		return insurance;
	}

	/**
	 * @return czy badanie się odbyło
	 */
	public Boolean isExamined() {
		return examined.get();
	}

	/**
	 * @param czy badanie się odbyło
	 */
	public void setExamined(boolean exam) {
		this.examined.set(exam);
	}

	/**
	 * @return uchwyt na pole czy badanie się odbyło
	 */
	public BooleanProperty examinedProperty() {
		return examined;
	}
	
	/**
	 * @return uchwyt na pole badanie
	 */
	public Examination getExamination() {
		return examination;
	}
	
	/**
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
	 */
	public void setExamination(Examination examination) {
		this.examination = examination;
		this.setExamined(true);
	}
}