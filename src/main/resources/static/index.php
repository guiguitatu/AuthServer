<?php
session_start();

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<body>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/index.css">

    <title>Index</title>
</head>
<div class="main">
    <?php
    echo ' <div style="display: flex; flex-direction: row;justify-content: center; align-items: center; margin: 20px 0 20px">
                   <a href="sairusuario.php" style="text-decoration: none;"> <button class="btnlimpa" style="background-color: #ec5858; margin-left:30px; font-size:20px;">Sair</button></a>
               </div>
             ';
    if (isset($_SESSION['erro'])) {
        echo '<div class="erro2">';
        foreach ($_SESSION['erro'] as $erro) {
            echo '<p>' . $erro . '</p>';
        }
        echo '</div><br>';
    }
    unset($_SESSION['erro']);

    echo '<form action=' . htmlspecialchars($_SERVER['PHP_SELF']) . ' method="post" class="form">';
    ?>
    <div style="display: none; flex-direction: row; align-items: center; justify-content: space-evenly; margin: 0 0 40px;">
        <div style="display: flex; align-items: center">
            <input type="radio" id="mesa" name="opcao" value="mesa" onchange="mudanome()" checked
                   style="width: 25px; height: 25px;">
            <label for="opcao1">Mesa</label>
        </div>
    </div>
    <label for="mesa" id="txtinput" style="display: flex; justify-content: center">Digite o numero da mesa:</label>
    <div class="btns">
        <input type="text" id="nunmesa" name="mesa" pattern="[0-9]+" title="Número da mesa" readonly class="input">
        <button type="button" class="btnlimpa" onclick="limpa()">Limpar</button>
    </div>

    <div class="btns">
        <button type="button" class="btnnums" onclick="mudanum(1)">1</button>
        <button type="button" class="btnnums" onclick="mudanum(2)">2</button>
        <button type="button" class="btnnums" onclick="mudanum(3)">3</button>
    </div>
    <div class="btns">
        <button type="button" class="btnnums" onclick="mudanum(4)">4</button>
        <button type="button" class="btnnums" onclick="mudanum(5)">5</button>
        <button type="button" class="btnnums" onclick="mudanum(6)">6</button>
    </div>
    <div class="btns">
        <button type="button" class="btnnums" onclick="mudanum(7)">7</button>
        <button type="button" class="btnnums" onclick="mudanum(8)">8</button>
        <button type="button" class="btnnums" onclick="mudanum(9)">9</button>
    </div>
    <div class="btns">
        <button type="button" class="zero" onclick="mudanum(0)">0</button>
        <button type="button" class="btnnums" onclick="backspace()">⌫</button>
    </div>
    <button type="submit" style="margin-top: 5px; font-size: 30px">Enviar</button>
    </form>
</div>
<header>
    <div style="position: absolute; z-index: 5; left: 15px; height: 95%; align-items: center; display: flex; justify-content: center">
        <a href="config.php">
            <button class="btnconfig"><img src="./imgs/config.png" style="height: 80%"/></button>
        </a>
    </div>
</header>
<script>

    function limpa() {
        let inp = document.getElementById('nunmesa');
        inp.value = ''
    }

    function mudanum(numero) {
        let inp = document.getElementById('nunmesa');
        if (inp.value.length < 4) {
            inp.value = inp.value + numero;
        }
    }

    function backspace() {
        let inp = document.getElementById('nunmesa');
        inp.value = inp.value.slice(0, -1);
    }

    function mudanome() {
        let mesa = document.getElementById("mesa");
        let ficha = document.getElementById("ficha");
        let label = document.getElementById("txtinput");

        if (mesa.checked) {
            label.textContent = "Digite o número da mesa:";
        } else if (ficha.checked) {
            label.textContent = "Digite o número da ficha:";
        }
    }
</script>
</body>
</html>