package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @BeforeEach
    void init() {
        ContactService.getInstance().database.clear();
    }

    @Test
    void testGetInstance() {
        assertThat(ContactService.getInstance()).isNotNull();
    }

    @Test
    void testAdd() throws Exception  {
        Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().add(contact)).isTrue();
        assertThat(ContactService.getInstance().database)
            .containsEntry("12345", contact);
    }

    @Test
    void testDelete() throws Exception {
        Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().add(contact)).isTrue();
        assertThat(ContactService.getInstance().delete("12345")).isTrue();
        assertThat(ContactService.getInstance().database)
            .doesNotContainEntry("12345", contact);
    }

    @Test
    void testUpdate() throws Exception {
        Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().add(contact)).isTrue();

        Contact updated = new Contact("99999", "Bob", "Barker", "9997771111", "5432 Test Lane");
        assertThat(ContactService.getInstance().update("12345", updated)).isTrue();

        Contact stored = ContactService.getInstance().database.get("12345");
        assertThat(stored)
            .hasFieldOrPropertyWithValue("firstName", "Bob")
            .hasFieldOrPropertyWithValue("lastName", "Barker")
            .hasFieldOrPropertyWithValue("phone", "9997771111")
            .hasFieldOrPropertyWithValue("address", "5432 Test Lane");
    }

    // ---------------- Failure scenarios ----------------

    @Test
    void testAddDuplicateIdReturnsFalse() throws Exception {
        Contact contact1 = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        Contact contact2 = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");

        assertThat(ContactService.getInstance().add(contact1)).isTrue();
        assertThat(ContactService.getInstance().add(contact2)).isFalse(); // duplicate ID
        assertThat(ContactService.getInstance().database).hasSize(1);
    }

    @Test
    void testDeleteNonExistentReturnsFalse() {
        // "12345" was never added in this test (DB cleared in @BeforeEach)
        assertThat(ContactService.getInstance().delete("12345")).isFalse();
    }

    @Test
    void testUpdateNonExistentReturnsFalse() throws Exception {
        // updating an id that doesn't exist should return false
        Contact updated = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().update("12345", updated)).isFalse();
    }

    @Test
    void testUpdateIgnoresUpdatedIdKeepsOriginalId() throws Exception {
        Contact original = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        ContactService.getInstance().add(original);

        // Service should keep the original key "12345", not create "DIFFERENT"
        Contact updated = new Contact("DIFFERENT", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().update("12345", updated)).isTrue();

        assertThat(ContactService.getInstance().database.containsKey("12345")).isTrue();
        assertThat(ContactService.getInstance().database.containsKey("DIFFERENT")).isFalse();
    }
}
