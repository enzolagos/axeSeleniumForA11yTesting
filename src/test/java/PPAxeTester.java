import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class PPAxeTester {
    @Test
    public void axeTesting() throws InterruptedException {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(ops);
        driver.get("https://www.petpartners.com/");
        AxeBuilder axeBuilder = new AxeBuilder();

        try {
            Results axeResults = axeBuilder.analyze(driver);
            List<Rule> violations = axeResults.getViolations();
            // List<Rule> passes = axeResults.getPasses();
            // List<Rule> incomplete = axeResults.getIncomplete();

            System.out.println("============VIOLATIONS============");
            for (Rule violation : violations) {
                System.out.println("Violation:");
                System.out.println("Description: " + violation.getDescription());
                System.out.println("Help: " + violation.getHelp());
                System.out.println("Impact: " + violation.getImpact());
                System.out.println("Nodes: " + violation.getNodes());
                System.out.println("Help URL: " + violation.getHelpUrl());
            }
            AxeReporter.writeResultsToJsonFile("resultado2",axeResults);

            driver.close();
            driver.quit();
        } catch (RuntimeException e) {
            System.out.println("Algo no anduvo");
            driver.close();
            driver.quit();
        }

        driver.quit();
    }
}
