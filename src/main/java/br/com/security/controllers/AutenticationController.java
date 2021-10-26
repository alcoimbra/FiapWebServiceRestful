package br.com.security.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.utils.JwtTokenUtil;
import br.com.stefanini.pontointeligente.dto.JwtAuthenticationDto;
import br.com.stefanini.pontointeligente.dto.TokenDto;
import io.swagger.models.Response;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping
	public ResponseEntity<Response<TokenDto>> gerarTokenJwt(
			@Valid @RequestBody JwtAuthenticationDto authenticationDto, BindingResult result) 
		throws AuthenticationException {
		Response<TokenDto> response = new Response<TokenDto>();
		
		if(result.hasErrors()) {
			log.error("Erro Validando Lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		log.info("Gerando Token para o E-mail {}", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getSenha()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = this.jwtTokenUtil.obterToken(userDetails);
		
		response.setData(new TokenDto(token));
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request){
		log.info("Gerando Refresh Token JWT");
		Response<TokenDto> response = new Response<TokenDto>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if(token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}
		
		if(!token.isPresent()) {
			response.getErrors().add("Token não Informado");
		} else if(!this.jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token Inválido ou Expirado");
		}
		
		if(!response.getErrors().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}
		
		String refreshedToken = this.jwtTokenUtil.refreshToken(token.get());
		
		response.setData(new TokenDto(refreshedToken));
		
		return ResponseEntity.ok(response);
	}
}