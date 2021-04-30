package it.objectmethod.ecommerce.services;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@Component
public class JWTService {

	private static final Logger logger = LogManager.getLogger(JWTService.class);

	private static final String MY_SECRET_JWT_KEY = "my-secret-jwt-key";

	public String createJWTToken(UtenteDTO utenteDto) {

		Calendar cal = Calendar.getInstance();

		int minutes = cal.get(Calendar.MINUTE) + 55;
		if (minutes > 60) {
			minutes = minutes - 60;
			cal.set(Calendar.HOUR, cal.get(Calendar.MINUTE) + 1);
		}
		cal.set(Calendar.MINUTE, minutes);

		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		String token = JWT.create().withClaim("id-utente", utenteDto.getId())
				.withClaim("nome-utente", utenteDto.getNome()).withExpiresAt(cal.getTime()).sign(alg);
		logger.info("Il token creato e:" + token);
		return token;
	}

	public boolean checkJWTToken(String jwtToken) {
		boolean valid = false;
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);

			Long idUtente = decoded.getClaim("id-utente").asLong();
			String nomeUtente = decoded.getClaim("nome-utente").asString();

			logger.info("Utente verificato! " + idUtente + " - " + nomeUtente);
			valid = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("IL TOKEN NON E VALIDO" + e);
		}

		return valid;
	}

	public UtenteDTO getUtenteByToken(String jwtToken) {

		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		Long idUtente = null;
		String nomeUtente;
		UtenteDTO utente = null;
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);
			idUtente = decoded.getClaim("id-utente").asLong();
			nomeUtente = decoded.getClaim("nome-utente").asString();
			utente = new UtenteDTO();
			utente.setId(idUtente);
			utente.setNome(nomeUtente);
			logger.info("utente con id [" + utente.getId() + " verificato nel token [" + jwtToken + "]");
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			logger.error("IL TOKEN NON E VALIDO" + e);
		}
		return utente;

	}

}
