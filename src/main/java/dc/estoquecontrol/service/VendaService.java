package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.CriarVendaRequest;
import dc.estoquecontrol.dto.response.ItemVendaResponseDTO;
import dc.estoquecontrol.dto.response.MostrarEntradaResponse;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.dto.response.MostrarVendaResponse;
import dc.estoquecontrol.entity.*;
import dc.estoquecontrol.repository.FuncionarioRepository;
import dc.estoquecontrol.repository.ProdutoRepository;
import dc.estoquecontrol.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Transactional
    public ResponseEntity criarVenda(CriarVendaRequest dto) {
        var funcionario = funcionarioRepository.getReferenceById(dto.idFuncionario());
        System.out.println("teste: " + funcionario);

        List<VendaProduto> vendaProdutos = dto.produtos().stream().map(produtoDTO -> {
            Produto produto = produtoRepository.getReferenceById(produtoDTO.idProduto());
            produto.subtrairQuantidade(produtoDTO.quantidade());
            BigDecimal valorCalculado = produto.getPreco().multiply(BigDecimal.valueOf(produtoDTO.quantidade()));

            return new VendaProduto(
                    null, // id gerado automaticamente
                    null, // a venda será setada depois
                    produto,
                    produtoDTO.quantidade(),
                    valorCalculado
            );
        }).collect(Collectors.toList());


        var venda = new Venda(dto, funcionario);

        // vincula os produtos à venda
        vendaProdutos.forEach(vp -> vp.setVenda(venda));
        venda.setVendaProdutos(vendaProdutos);

        vendaRepository.save(venda);

        List<ItemVendaResponseDTO> itens = venda.getVendaProdutos().stream().map(vp ->
                new ItemVendaResponseDTO(
                        vp.getProduto().getNome(),
                        vp.getQuantidade(),
                        vp.getValor()
                )
        ).toList();

        var vendaResponse = new MostrarVendaResponse(venda, funcionario, itens);

        return ResponseEntity.ok(vendaResponse);
    }

    public ResponseEntity<Page<MostrarVendaResponse>> listarTodasAsvendas(Pageable pageable) {
        Page<MostrarVendaResponse> page = vendaRepository.findAll(pageable)
                .map(venda -> {
                    Funcionario funcionario = venda.getFuncionario();

                    List<ItemVendaResponseDTO> itens = venda.getVendaProdutos().stream()
                            .map(vp -> new ItemVendaResponseDTO(
                                    vp.getProduto().getNome(),
                                    vp.getQuantidade(),
                                    vp.getValor()
                            ))
                            .toList();

                    return new MostrarVendaResponse(venda, funcionario, itens);
                });

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorMesEAno(Integer mes, Integer ano, Pageable pageable) {
        Page<MostrarVendaResponse> page = vendaRepository.findByMesEAno(mes, ano, pageable)
                .map(venda -> {
                    Funcionario funcionario = venda.getFuncionario();

                    List<ItemVendaResponseDTO> itens = venda.getVendaProdutos().stream()
                            .map(vp -> new ItemVendaResponseDTO(
                                    vp.getProduto().getNome(),
                                    vp.getQuantidade(),
                                    vp.getValor()
                            ))
                            .toList();

                    return new MostrarVendaResponse(venda, funcionario, itens);
                });

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorMes(Integer mes, Pageable pageable) {
        Page<MostrarVendaResponse> page = vendaRepository.findByMes(mes, pageable)
                .map(venda -> {
                    Funcionario funcionario = venda.getFuncionario();

                    List<ItemVendaResponseDTO> itens = venda.getVendaProdutos().stream()
                            .map(vp -> new ItemVendaResponseDTO(
                                    vp.getProduto().getNome(),
                                    vp.getQuantidade(),
                                    vp.getValor()
                            ))
                            .toList();

                    return new MostrarVendaResponse(venda, funcionario, itens);
                });

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorAno(Integer ano, Pageable pageable) {
        Page<MostrarVendaResponse> page = vendaRepository.findByAno(ano, pageable)
                .map(venda -> {
                    Funcionario funcionario = venda.getFuncionario();

                    List<ItemVendaResponseDTO> itens = venda.getVendaProdutos().stream()
                            .map(vp -> new ItemVendaResponseDTO(
                                    vp.getProduto().getNome(),
                                    vp.getQuantidade(),
                                    vp.getValor()
                            ))
                            .toList();

                    return new MostrarVendaResponse(venda, funcionario, itens);
                });

        return ResponseEntity.ok(page);
    }
}
