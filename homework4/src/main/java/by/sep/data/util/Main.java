package by.sep.data.util;

import by.sep.data.dao.*;
import by.sep.data.pojos.character.Character;
import by.sep.data.pojos.character.CharacterStatistics;
import by.sep.data.pojos.character.Warrior;
import by.sep.data.pojos.employee.Employee;
import by.sep.data.pojos.employee.EmployeeDetails;
import by.sep.data.pojos.employee.Manager;
import by.sep.data.pojos.insurance.Insurance;
import by.sep.data.pojos.insurance.InsuranceInfo;
import by.sep.data.pojos.insurance.TravelInsurance;
import org.hibernate.Session;

import java.sql.Date;

public class Main {
    private static final CharacterDao CHARACTER_DAO = new CharacterDaoImpl(Hw4SessionFactory.getSessionFactory());
    private static final EmployeeDao EMPLOYEE_DAO = new EmployeeDaoImpl(Hw4SessionFactory.getSessionFactory());
    private static final InsuranceDao INSURANCE_DAO = new InsuranceDaoImpl(Hw4SessionFactory.getSessionFactory());
    private static final Character WARRIOR = new Warrior(null, "Leroy",
            new CharacterStatistics(100, 10, 5), 80);
    private static final Employee MANAGER = new Manager(null, "Peter", "Black",
            new EmployeeDetails(3000.00, Date.valueOf("2015-06-30"), Date.valueOf("1982-02-15")),
            "S8569");
    private static final Insurance TRAVEL_INSURANCE = new TravelInsurance(null, "Bolton",
            new InsuranceInfo(300.45, 3),
            "Japan", "N008956");

    public static void main(String[] args) {
        try {
            pushDataIntoDatabase();
            printIdentifiers();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Program failed with exception: " + e.getMessage());
        }
    }

    private static void pushDataIntoDatabase() {
        CHARACTER_DAO.create(WARRIOR);
        EMPLOYEE_DAO.create(MANAGER);
        INSURANCE_DAO.create(TRAVEL_INSURANCE);
    }

    private static void printIdentifiers() {
        try (Session session = Hw4SessionFactory.getSessionFactory().openSession()) {
            session.refresh(WARRIOR);
            session.refresh(MANAGER);
            session.refresh(TRAVEL_INSURANCE);
            System.out.println("Increment strategy ID - " + session.getIdentifier(WARRIOR));
            System.out.println("Seqhilo strategy ID - " + session.getIdentifier(MANAGER));
            System.out.println("UUID strategy ID - " + session.getIdentifier(TRAVEL_INSURANCE));
        }
    }
}