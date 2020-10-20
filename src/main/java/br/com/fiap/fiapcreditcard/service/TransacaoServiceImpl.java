package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.TransacaoCreateDTO;
import br.com.fiap.fiapcreditcard.dto.TransacaoDTO;
import br.com.fiap.fiapcreditcard.model.Aluno;
import br.com.fiap.fiapcreditcard.model.Transacao;
import br.com.fiap.fiapcreditcard.repository.AlunoRepository;
import br.com.fiap.fiapcreditcard.repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private TransacaoRepository transacaoRepository;
    private AlunoRepository alunoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository, AlunoRepository alunoRepository) {

        this.transacaoRepository = transacaoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<TransacaoDTO> findAll() {
        List<TransacaoDTO> transacaoList = transacaoRepository.findAll()
                .stream()
                .map(transacao -> new TransacaoDTO(transacao))
                .collect(Collectors.toList());
        return transacaoList;
    }

    @Override
    public List<TransacaoDTO> findByAluno(Long id) {
        List<TransacaoDTO> transacaoList = transacaoRepository.findByAluno(id)
                .stream()
                .map(transacao -> new TransacaoDTO(transacao))
                .collect(Collectors.toList());
        return transacaoList;
    }

    @Override
    public TransacaoDTO create(TransacaoCreateDTO transacaoCreateDTO) {
        Transacao transacao = new Transacao();
        transacao.setProduto(transacaoCreateDTO.getProduto());
        transacao.setBeneficiario(transacaoCreateDTO.getBeneficiario());
        transacao.setValor(transacaoCreateDTO.getValor());
        Aluno aluno = getAlunoById(transacaoCreateDTO);
        transacao.setAluno(aluno);

        Transacao savedTransacao = transacaoRepository.save(transacao);
        return new TransacaoDTO(savedTransacao);
    }

    private Aluno getAlunoById(TransacaoCreateDTO transacaoCreateDTO) {
        return alunoRepository.findByIdAndAtivoIsTrue(transacaoCreateDTO.getIdAluno())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
