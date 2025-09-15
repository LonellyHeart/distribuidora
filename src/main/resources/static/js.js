const quantidade = document.getElementById('quantidade');
const valorUnitario = document.getElementById('valorUnitario');
const desconto = document.getElementById('desconto');
const valorTotal = document.getElementById('valorTotal');

function calcularTotal() {
  const quant = parseFloat(quantidade.value) || 0;
  const valorUni = parseFloat(valorUnitario.value) || 0;
  const desc = parseFloat(desconto.value) || 0;

  let total = quant * valorUni;
  if (desc > 0 && desc <= 100) { /* A segunda regra serve somente para ignorar caso o valor exceda 100, para evitar nÃºmeros negativos */
    total -= total * (desc / 100);
  }

  valorTotal.textContent = total.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  });
}

function resetarValor() {
  valorTotal.textContent = 'R$ 0,00';
}

quantidade.addEventListener('input', calcularTotal);
valorUnitario.addEventListener('input', calcularTotal);
desconto.addEventListener('input', calcularTotal);