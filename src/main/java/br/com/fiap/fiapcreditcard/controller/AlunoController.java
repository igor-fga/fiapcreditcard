package br.com.fiap.fiapcreditcard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;
import br.com.fiap.fiapcreditcard.dto.AlunoDTO;

@RestController
@RequestMapping("alunos")
public class AlunoController {

	private List<AlunoDTO> alunoDTOList = new ArrayList<>();

	@GetMapping
	public List<AlunoDTO> findAll(@RequestParam(required = false, value = "nome") String nome) {

		return alunoDTOList.stream()
				.filter(alunoDTO -> nome == null || alunoDTO.getNome().toUpperCase().contains(nome.toUpperCase()))
				.filter(alunoDTO -> alunoDTO.getAtivo()).collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public AlunoDTO getById(@PathVariable Long id) {
		return alunoDTOList.stream().filter(alunoDTO -> alunoDTO.getId().equals(id) && alunoDTO.getAtivo()).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoDTO create(@RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO.setId(alunoDTOList.size() + 1L);
		alunoDTO.setNome(alunoCreateUpdateDTO.getNome());
		alunoDTO.setNumeroCartao(alunoCreateUpdateDTO.getNumeroCartao());
		alunoDTO.setAtivo(true);

		alunoDTOList.add(alunoDTO);
		return alunoDTO;
	}

	@PutMapping("{id}")
	public AlunoDTO update(@RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO, @PathVariable Long id) {
		AlunoDTO alunoDTO = getById(id);
		alunoDTO.setNome(alunoCreateUpdateDTO.getNome());
		alunoDTO.setNumeroCartao(alunoCreateUpdateDTO.getNumeroCartao());

		return alunoDTO;
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		AlunoDTO alunoDTO = getById(id);
		alunoDTO.setAtivo(false);
	}

	// Mock List
	public AlunoController() {
		AlunoDTO alunoDTO1 = new AlunoDTO();
		alunoDTO1.setId(1L);
		alunoDTO1.setNome("Igor");
		alunoDTO1.setNumeroCartao("202020202020");
		alunoDTO1.setAtivo(true);

		AlunoDTO alunoDTO2 = new AlunoDTO();
		alunoDTO2.setId(2L);
		alunoDTO2.setNome("Darth Vader");
		alunoDTO2.setNumeroCartao("212121212121");
		alunoDTO2.setAtivo(true);

		alunoDTOList.add(alunoDTO1);
		alunoDTOList.add(alunoDTO2);
	}
}
