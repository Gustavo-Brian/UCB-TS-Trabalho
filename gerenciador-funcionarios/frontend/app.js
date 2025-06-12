// app.js
const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const app = express();

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, ''));
app.set('views', [
  path.join(__dirname, 'geralviews'),
  path.join(__dirname, 'funcionario', 'views'),
]);

app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const funcionariosRouter = require('./funcionario/routes/funcionarios');
const perfilFuncionarioRouter = require('./funcionario/routes/perfil-funcionario');
const cadastroRouter = require('./funcionario/routes/funcionarios_cadastro');

app.get('/', (req, res) => {
  res.redirect('/funcionario');
});

app.use('/funcionario', funcionariosRouter);
app.use('/funcionario', perfilFuncionarioRouter);
app.use('/funcionario', cadastroRouter);

app.use((req, res, next) => {
  res.status(404).render('error', {
    message: 'Página não encontrada',
    error: {}
  });
});

app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).render('error', {
    message: 'Erro interno do servidor',
    error: err
  });
});

const PORT = process.env.PORT || 19133;
app.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});



module.exports = app;
