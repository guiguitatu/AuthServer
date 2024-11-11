<?php
session_start();
date_default_timezone_set('America/Sao_Paulo');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $garcon = isset($_POST['garcon']) ? $_POST['garcon'] : '';
    $senha = isset($_POST['password']) ? $_POST['password'] : '';
    $nome = isset($_POST['nome']) ? $_POST['nome'] : '';

    if (empty($garcon)) {
        $_SESSION['erro'][] = 'Selecione um garçom';
    } elseif (empty($senha)) {
        $_SESSION['erro'][] = 'Digite a senha';
    } else {
        $data = [
            'email' => $garcon,
            'password' => $senha
        ];

        $ch = curl_init('http://localhost:8080/api/users/login');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        $response = curl_exec($ch);
        curl_close($ch);

        $result = json_decode($response, true);

        setcookie('token', $result['token'], time() + 60 * 60 * 24, '/');
        setcookie('role', $nome, time() + 60 * 60 * 24, '/');
        header('Location: index.php');
        exit();

    }

}

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, "http://localhost:8080/api/users");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);

if (curl_errno($ch)) {
    $_SESSION['erro'][] = 'Error 500 - Internal Server Error';
} else {
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    if ($http_code == 500) {
        $_SESSION['erro'][] = 'Error 500 - Internal Server Error';
    } else {
        $users = json_decode($response, true);
    }
}

curl_close($ch);


?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Astraconbar</title>
    <meta charset="UTF-8" name="viewport" content="user-scalable=no">
    <link rel="stylesheet" href="./CSS/index.css">
</head>
<body style="justify-content: center">
<main class="principal">
    <div class="conteudo">
        <h2> Faça seu login </h2><br>
        <?php if ($_SESSION['erro']): ?>
            <div class="erro">
                <?php foreach ($_SESSION['erro'] as $erro): ?>
                    <p><?= $erro ?></p>
                <?php endforeach ?>
            </div> <br>
        <?php endif;
        unset($_SESSION['erro']);
        ?>
        <form action="#" method="post" class="login">
            <div class="input">
                <label for="garcon">Usuário:</label>
                <select name="garcon" id="garcon" onchange="mudaimput(this)">
                    <option value="">Selecione um garçom</option>
                    <?php
                    foreach ($users as $user) {
                        echo "<option value='{$user['email']}' data-name='{$user['name']}'>{$user['name']}</option>";
                    } ?>
                </select>
                <input name='nome' id='inputName' type='hidden' value='nome'>
            </div>

            <br>
            <div class="input">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="password" required style="margin-right: 0">
            </div>
            <br><br>
            <button type="submit" class="btnentrar">Entrar</button>
        </form>
    </div>
</main>
</body>
<script>
    function mudaimput(selectElement) {
    let input = document.getElementById('inputName');
    let selectedOption = selectElement.options[selectElement.selectedIndex];
    input.value = selectedOption.getAttribute('data-name');
}
</script>
</html>
