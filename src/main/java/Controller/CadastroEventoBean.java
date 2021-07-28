package Controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import model.Evento;
import model.Usuario;

public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	private Evento evento;

	// @Inject
	// private CadastroPedidoService cadastroPedidoService;

	private String sku;

	private List<Usuario> eventos;

	public CadastroEventoBean() {
		limpar();
	}

	public void inicializar() {
			if (this.usuario == null) {
				limpar();
			}
			
			
		public void clienteSelecionado(SelectEvent event) {
			usuario.setCliente((Cliente) event.getObject());
		}
		
		private void limpar() {
			usuario = new Pedido();
			usuario.setEnderecoEntrega(new EnderecoEntrega());
		}
		
		public void usuarioAlterado(@Observes PedidoAlteradoEvent event) {
			this.usuario = event.getPedido();
		}
		
		public void salvar() {
			this.usuario.removerItemVazio();
			
			try {
				this.usuario = this.cadastroPedidoService.salvar(this.usuario);
			
				FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
			} catch (NegocioException ne) {
				FacesUtil.addErrorMessage(ne.getMessage());
			} finally {
				this.usuario.adicionarItemVazio();
			}
		}
		
		public void recalcularPedido() {
			if (this.usuario != null) {
				this.usuario.recalcularValorTotal();
			}
		}
		
		public void carregarProdutoPorSku() {
			if (StringUtils.isNotEmpty(this.sku)) {
				this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
				this.carregarProdutoLinhaEditavel();
			}
		}
		
		public void carregarProdutoLinhaEditavel() {
			ItemPedido item = this.usuario.getItens().get(0);
			
			if (this.produtoLinhaEditavel != null) {
				if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
					FacesUtil.addErrorMessage("Já existe um item no usuario com o produto informado.");
				} else {
					item.setProduto(this.produtoLinhaEditavel);
					item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
					
					this.usuario.adicionarItemVazio();
					this.produtoLinhaEditavel = null;
					this.sku = null;
					
					this.usuario.recalcularValorTotal();
				}
			}
		}
		
		private boolean existeItemComProduto(Produto produto) {
			boolean existeItem = false;
			
			for (ItemPedido item : this.getPedido().getItens()) {
				if (produto.equals(item.getProduto())) {
					existeItem = true;
					break;
				}
			}
			
			return existeItem;
		}

		public List<Produto> completarProduto(String nome) {
			return this.produtos.porNome(nome);
		}
		
		public void atualizarQuantidade(ItemPedido item, int linha) {
			if (item.getQuantidade() < 1) {
				if (linha == 0) {
					item.setQuantidade(1);
				} else {
					this.getPedido().getItens().remove(linha);
				}
			}
			
			this.usuario.recalcularValorTotal();
		}
		
		public FormaPagamento[] getFormasPagamento() {
			return FormaPagamento.values();
		}
		
		public List<Cliente> completarCliente(String nome) {
			return this.clientes.porNome(nome);
		}

		public Pedido getPedido() {
			return usuario;
		}
		
		public void setPedido(Pedido usuario) {
			this.usuario = usuario;
		}

		public List<Usuario> getVendedores() {
			return vendedores;
		}
		
		public boolean isEditando() {
			return this.usuario.getId() != null;
		}

		public Produto getProdutoLinhaEditavel() {
			return produtoLinhaEditavel;
		}

		public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
			this.produtoLinhaEditavel = produtoLinhaEditavel;
		}

		@SKU
		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}
		
		@NotBlank
		public String getNomeCliente() {
			return usuario.getCliente() == null ? null : usuario.getCliente().getNome();
		}
		
		public void setNomeCliente(String nome) {
		}

	}
}
