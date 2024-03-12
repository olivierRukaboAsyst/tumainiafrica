package anubis.lab.anubisproject;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.repository.ArticleRepository;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.repository.CategoryRepository;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.comment.repository.CommentRepository;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import anubis.lab.anubisproject.features.customer.repository.CustomerRepository;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;

@SpringBootApplication
public class AnubisprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnubisprojectApplication.class, args);
	}

	// @Bean
	CommandLineRunner start(UtilisateurRepository utilisateurRepository, ArticleRepository articleRepository,
			CategoryRepository categoryRepository, CustomerRepository customerRepository,
			CommentRepository commentRepository) {
		return args -> {
			for (int i = 0; i < 3; i++) {
				Utilisateur utilisateur = new Utilisateur();
				Random r = new Random();
				utilisateur.setFirstname("User" + i);
				utilisateur.setLastname("Name");
				utilisateur.setEmail((utilisateur.getFirstname()).toLowerCase()
						+ (utilisateur.getLastname()).toLowerCase() + "@anubis.lab");
				utilisateur.setPassword("olivier");
//				Role[] roles = Role.values();
//				Role role = roles[r.nextInt(roles.length)];
//				utilisateur.setRoles(role);

				utilisateurRepository.save(utilisateur);
			}
			List<String> names = Arrays.asList("Sport", "Politique", "Divers");
			names.forEach(n -> {
				Category category = new Category();
				category.setName(n);
				category.setDescription("Descpriction pour " + n);
				categoryRepository.save(category);
			});
			List<Category> categories = categoryRepository.findAll();
			utilisateurRepository.findAll().forEach(u -> {
				categories.forEach(c -> {
					for (int i = 0; i < 5; i++) {
						Article article = new Article();
						article.setTitle("Organisation des nations unies");
						article.setContent(
								"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
//						article.setCategory(c);
//						article.setUtilisateur(u);
						articleRepository.save(article);
					}
				});
			});
			for (int i = 0; i < 4; i++) {
				String name = "Customer";
				Customer customer = new Customer();
				customer.setFullname("full "+name + i);
				customer.setEmail((name + "@gmail.com").toLowerCase());
				customerRepository.save(customer);
			}
			List<Article> articles = articleRepository.findAll();
			List<Customer> customers = customerRepository.findAll();

			articles.forEach(a -> {
				customers.forEach(c -> {
					Comment comment = new Comment();
					comment.setContent(
							"Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud");
					comment.setArticle(a);
					comment.setCustomer(c);
					commentRepository.save(comment);
				});
			});
		};
	}

}
