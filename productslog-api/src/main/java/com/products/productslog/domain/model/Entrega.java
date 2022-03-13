package com.products.productslog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity

public class Entrega {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@ManyToOne
		private Cliente cliente;
		
		
		@Embedded
		private Destinatario destinatario;
		
		@NotNull
		private BigDecimal taxa;
		
		@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
		List<Ocorrencia> ocorrencias = new ArrayList<>();
		
		@JsonProperty(access = Access.READ_ONLY)
		@Enumerated(EnumType.STRING)
		private StatusEntrega status;
		@JsonProperty(access = Access.READ_ONLY)
		private OffsetDateTime dataPedido;
		
		@JsonProperty(access = Access.READ_ONLY)
		private OffsetDateTime dataFinalizacao;
		
		
		public List<Ocorrencia> getOcorrencias() {
			return ocorrencias;
		}
		public void setOcorrencias(List<Ocorrencia> ocorrencias) {
			this.ocorrencias = ocorrencias;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		public Destinatario getDestinatario() {
			return destinatario;
		}
		public void setDestinatario(Destinatario destinatario) {
			this.destinatario = destinatario;
		}
		public BigDecimal getTaxa() {
			return taxa;
		}
		public void setTaxa(BigDecimal taxa) {
			this.taxa = taxa;
		}
		public OffsetDateTime getDataPedido() {
			return dataPedido;
		}
		public void setDataPedido(OffsetDateTime dataPedido) {
			this.dataPedido = dataPedido;
		}
		public OffsetDateTime getDataFinalizacao() {
			return dataFinalizacao;
		}
		public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
			this.dataFinalizacao = dataFinalizacao;
		}
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		public StatusEntrega getStatus() {
			return status;
		}
		public void setStatus(StatusEntrega status) {
			this.status = status;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entrega other = (Entrega) obj;
			return Objects.equals(id, other.id);
		}
		public Ocorrencia adicionarOcorrencia(String descricao) {
			Ocorrencia ocorrencia = new Ocorrencia();
			ocorrencia.setDescricao(descricao);
			ocorrencia.setDataRegistro(OffsetDateTime.now());
			ocorrencia.setEntrega(this);
			this.getOcorrencias().add(ocorrencia);
			
			return ocorrencia;
			
			
			
		}
		
		
		
		
		
}
