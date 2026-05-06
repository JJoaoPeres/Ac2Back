document.addEventListener('DOMContentLoaded', () => {
    const formProjeto     = document.getElementById('formProjeto');
    const formFuncionario = document.getElementById('formFuncionario');
    const corpoTabela     = document.getElementById('corpoTabela');
    const selectProjeto   = document.getElementById('projetoAssociado');
    const msgVazia        = document.getElementById('msgVazia');

    // Dados ficam aqui, sem localStorage
    let projetos = [];

    const listarProjetos = () => {
        corpoTabela.innerHTML = '';

        if (projetos.length === 0) {
            msgVazia.style.display = 'block';
            return;
        }

        msgVazia.style.display = 'none';

        projetos.forEach(projeto => {
            const row = corpoTabela.insertRow();
            row.innerHTML = `
                <td>${projeto.nome}</td>
                <td>${projeto.descricao}</td>
                <td>${projeto.inicio}</td>
                <td>${projeto.fim}</td>
                <td>${projeto.funcionarios.length > 0 ? projeto.funcionarios.join(', ') : 'Nenhum'}</td>
            `;
        });
    };

    const carregarProjetosNoSelect = () => {
        selectProjeto.innerHTML = '<option value="">-- Selecione um projeto --</option>';

        projetos.forEach(projeto => {
            const option = document.createElement('option');
            option.value = projeto.nome;
            option.textContent = projeto.nome;
            selectProjeto.appendChild(option);
        });
    };

    formProjeto.addEventListener('submit', (e) => {
        e.preventDefault();

        const nome      = document.getElementById('nomeProjeto').value.trim();
        const descricao = document.getElementById('descricao').value.trim();
        const inicio    = document.getElementById('inicio').value;
        const fim       = document.getElementById('fim').value;

        projetos.push({ nome, descricao, inicio, fim, funcionarios: [] });

        formProjeto.reset();
        carregarProjetosNoSelect();
        listarProjetos();
    });

    formFuncionario.addEventListener('submit', (e) => {
        e.preventDefault();

        const nomeFuncionario  = document.getElementById('nomeFuncionario').value.trim();
        const projetoAssociado = document.getElementById('projetoAssociado').value;

        if (!projetoAssociado) {
            alert('Selecione um projeto para o funcionário!');
            return;
        }

        const projeto = projetos.find(p => p.nome === projetoAssociado);
        if (projeto) {
            projeto.funcionarios.push(nomeFuncionario);
        }

        formFuncionario.reset();
        carregarProjetosNoSelect();
        listarProjetos();
    });

    listarProjetos();
});