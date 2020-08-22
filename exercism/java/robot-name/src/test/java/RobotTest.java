import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotTest {

    private static final String EXPECTED_ROBOT_NAME_PATTERN = "[A-Z]{2}\\d{3}";
    private Robot robot;

    @Before
    public void setUp() {
        robot = new Robot();
    }

    @Test
    public void hasName() {
        assertIsValidName(robot.getName());
    }

    @Test
    public void differentRobotsHaveDifferentNames() {
        assertThat(robot.getName()).isNotEqualTo(new Robot().getName());
    }

    @Test
    public void resetName() {
        final String name = robot.getName();
        robot.reset();
        final String name2 = robot.getName();
        assertThat(name).isNotEqualTo(name2);
        assertIsValidName(name2);
    }

    @Test
    public void differentRobotsHaveDifferentNamesRepeated() {
        for (int i = 0; i < 100000; i++) {
            assertThat(robot.getName()).isNotEqualTo(new Robot().getName());
        }
    }

    private static void assertIsValidName(String name) {
        assertThat(name).matches(EXPECTED_ROBOT_NAME_PATTERN);
    }
}
