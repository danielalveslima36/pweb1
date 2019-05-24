<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="vendor/jquery-ui/jquery-ui.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" action="cadastrar" id="signup-form" class="signup-form">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="primeiroNome">Primeiro nome</label>
                                <input type="text" class="form-input" name="primeiroNome" id="primeiroNome" />
                            </div>
                            <div class="form-group">
                                <label for="ultimoNome">Último nome</label>
                                <input type="text" class="form-input" name="ultimoNome" id="ultimoNome" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group form-icon">
                                <label for="nascimento">Data de Nascimento</label>
                                <input type="text" class="form-input" name="nascimento" id="nascimento" placeholder="MM-DD-YYYY" />
                            </div>
                            <div class="form-radio">
                                <label>Gênero</label>
                                <div class="form-flex">
                                    <input type="radio" name="genero" value="masculino" id="masculino" checked="checked" />
                                    <label for="masculino">Masculino</label>
    
                                    <input type="radio" name="genero" value="feminino" id="feminino" />
                                    <label for="feminino">Feminino</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telefone">Número de Telefone</label>
                            <input type="number" class="form-input" name="telefone" id="telefone" />
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="password" class="form-input" name="senha" id="senha"/>
                            </div>
                            <div class="form-group">
                                <label for="re_senha">Repita sua senha</label>
                                <input type="password" class="form-input" name="re_senha" id="re_senha"/>
                            </div>
                        </div>
                        <div class="form-text">
                            <a href="#" class="add-info-link"><i class="zmdi zmdi-chevron-right"></i>Additional info</a>
                            <div class="add_info">
                                <div class="form-group">
                                    <label for="email">E-mail</label>
                                    <input type="email" class="form-input" name="email" id="email"/>
                                </div>
                                <div class="form-group">
                                    <label for="pais">País</label>
                                    <div class="select-list">
                                        <select name="pais" id="pais" required>
                                            <option value="BR">Brasil</option>
                                            <option value="PT">Portugal</option>
                                            <option value="ES">Espanha</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="cidade">Cidade</label>
                                    <div class="select-list">
                                        <select name="cidade" id="cidade" required>
                                            <option value="CJZ">Cajazeiras</option>
                                            <option value="JP">João Pessoa</option>
                                            <option value="CG">Campina Grande</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Enviar"/>
                        </div>
                    </form>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="vendor/jquery-validation/dist/additional-methods.min.js"></script>
    <script src="js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>