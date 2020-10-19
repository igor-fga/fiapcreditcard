package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;
import br.com.fiap.fiapcreditcard.dto.AlunoDTO;
import br.com.fiap.fiapcreditcard.model.Aluno;
import br.com.fiap.fiapcreditcard.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    private List<AlunoDTO> alunoDTOList = new ArrayList<>();
    private AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository) {

        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<AlunoDTO> findAll(String search) {
        String searchTerm = search == null ? "" : search;
        return alunoRepository.buscaPorNome(searchTerm)
                .stream()
                .map(aluno -> new AlunoDTO(aluno))
                .collect(Collectors.toList());
    }

    @Override
    public AlunoDTO findById(Long id) {

        return alunoDTOList.stream().filter(alunoDTO -> alunoDTO.getId().equals(id) && alunoDTO.getAtivo()).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public AlunoDTO create(AlunoCreateUpdateDTO alunoCreateUpdateDTO) {

        Aluno aluno = new Aluno(alunoCreateUpdateDTO);
        Aluno savedAluno = alunoRepository.save(aluno);

        return new AlunoDTO(savedAluno);
    }

    @Override
    public AlunoDTO update(AlunoCreateUpdateDTO alunoCreateUpdateDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
