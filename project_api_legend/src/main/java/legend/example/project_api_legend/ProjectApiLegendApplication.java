package legend.example.project_api_legend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @EnableJpaRepositories(basePackages = "legend.example.project_api_legend.Repository.Login")
public class ProjectApiLegendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiLegendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
            // URL of the HTML file (e.g., http://localhost:8080/index.html)
            String url = "http://localhost:8080/Html/index.html";

            // Check the operating system
            String os = System.getProperty("os.name").toLowerCase();
            try {
                if (os.contains("win")) {
                    // For Windows
                    Runtime.getRuntime().exec("cmd /c start chrome " + url);
                } else if (os.contains("mac")) {
                    // For macOS
                    Runtime.getRuntime().exec("open -a \"Google Chrome\" " + url);
                } else if (os.contains("nix") || os.contains("nux")) {
                    // For Linux
                    Runtime.getRuntime().exec("google-chrome " + url);
                }
            } catch (Exception e) {
                System.err.println("Failed to open browser: " + e.getMessage());
            }
        };
	}

}
