
//---TABELA PRODUTOS---
//
// JS para fazer o armazenemento do fornecedor e salvar na tabela produtos.
document.addEventListener("DOMContentLoaded", function() {
  const fornecedorSelect = document.getElementById("fornecedorSelect");
  const fornecedorHidden = document.getElementById("fornecedor");

  if (fornecedorSelect) {
    fornecedorSelect.addEventListener("change", function() {
      const selectedOption = this.options[this.selectedIndex];
      fornecedorHidden.value = selectedOption.getAttribute("data-nome");
    });
  }
  
  //---TABELA VENDAS---

  // --- Lógica de preenchimento automático dos campos do cliente ---
  const clienteSelect = document.getElementById("clienteSelect");
  const cpf = document.getElementById("cpf");
  const contato = document.getElementById("contato");
  const endereco = document.getElementById("endereco");
  const email = document.getElementById("email");

  if (clienteSelect) {
    clienteSelect.addEventListener("change", function() {
      const selected = this.options[this.selectedIndex];
      cpf.value = selected.dataset.cpf || '';
      contato.value = selected.dataset.contato || '';
      endereco.value = selected.dataset.endereco || '';
      email.value = selected.dataset.email || '';
    });
  }

  // --- Lógica de cálculo automático do total ---
  const quantidadeInput = document.getElementById("quantidade");
  const unitarioInput = document.getElementById("unitario");
  const descontoInput = document.getElementById("desconto");
  const totalInput = document.getElementById("total");

  function calcularTotal() {
    const quantidade = parseFloat(quantidadeInput.value) || 0;
    const unitario = parseFloat(unitarioInput.value) || 0;
    let desconto = parseFloat(descontoInput.value) || 0;

    if (desconto > 100) desconto = 100; // Essa condicional serve somente para caso coloque desconto maior que 100. Como por exemplo 250, o valor a ser considerado ainda é 100.

    const total = quantidade * unitario * (1 - desconto / 100);
    totalInput.value = total.toFixed(2); // Formatação de casa decimal
  }

  if (quantidadeInput && unitarioInput && descontoInput && totalInput) {
    quantidadeInput.addEventListener("input", calcularTotal);
    unitarioInput.addEventListener("input", calcularTotal);
    descontoInput.addEventListener("input", calcularTotal);
  }
});