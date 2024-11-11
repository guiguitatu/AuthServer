<?php
session_start();

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $mesa = isset($_POST['mesa']) ? $_POST['mesa'] : '';

    if (empty($mesa)) {
        $_SESSION['erro'][] = 'Digite o número da mesa';
        header('Location: index.php');
    } else {
        $_SESSION['mesa'] = $mesa;
        header('Location: abremesa.php');
        exit();
    }
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
    <button type="submit" style="width: 100% ;margin-top: 5px; font-size: 30px">Enviar</button>
    </form>
</div>
<?php
if ($_COOKIE['role']) {
    echo '
        <header>
        <div style="position: absolute; z-index: 5; left: 15px; height: 95%; align-items: center; display: flex; justify-content: center">
        <a href="adm.php" style="text-decoration: none;"><button class="btnconfig">ADM</button></a>
        </div>
        </header>';
}
?>
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

</script>
</body>
</html>