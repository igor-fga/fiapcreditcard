package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.TransacaoCreateDTO;
import br.com.fiap.fiapcreditcard.dto.TransacaoDTO;

import java.util.List;

public interface TransacaoService {

    List<TransacaoDTO> findAll();

    List<TransacaoDTO> findByAluno(Long id);

    TransacaoDTO create(TransacaoCreateDTO transacaoCreateDTO);
}
