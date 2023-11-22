import com.project.SafetyNet.controller.dto.PersonSummray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PersonSummrayTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet PersonSummary avec des valeurs fictives
        PersonSummray personSummray = new PersonSummray();
        personSummray.setFirstName("John");
        personSummray.setLastName("Doe");
        personSummray.setAddress("123 Main St");
        personSummray.setPhone("555-1234");

        // Vérification des méthodes getters
        assertEquals("John", personSummray.getFirstName());
        assertEquals("Doe", personSummray.getLastName());
        assertEquals("123 Main St", personSummray.getAddress());
        assertEquals("555-1234", personSummray.getPhone());

        // Vérification des méthodes setters
        PersonSummray updatedPersonSummray = new PersonSummray();
        updatedPersonSummray.setFirstName("Jane");
        updatedPersonSummray.setLastName("Smith");
        updatedPersonSummray.setAddress("456 Oak St");
        updatedPersonSummray.setPhone("555-5678");

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals("Jane", updatedPersonSummray.getFirstName());
        assertEquals("Smith", updatedPersonSummray.getLastName());
        assertEquals("456 Oak St", updatedPersonSummray.getAddress());
        assertEquals("555-5678", updatedPersonSummray.getPhone());
    }


}
