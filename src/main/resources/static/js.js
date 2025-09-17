
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
});
