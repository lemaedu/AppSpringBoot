package ec.gob.consejodecomunicacion.seleccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SeleccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleccionApplication.class, args);
	}

}
