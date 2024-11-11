<?php
session_start();

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}

if (!$_COOKIE['role']) {
    header('Location: index.php');
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $data = [
        'id' => $_POST['id'],
        'descricao' => $_POST['descricao'],
        'preco' => $_POST['preco'],
        'codGruEst' => $_POST['codGruEst']
    ];

    $url = 'http://localhost:8080/api/produto/' . $_POST['id'];

    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        'Content-Type: application/json',
        'Authorization: Bearer ' . $_COOKIE['token']
    ]);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
    $response = curl_exec($ch);

    header('Location: adm.php');
    exit();
}

$cod = 'http://localhost:8080/api/codgruest';
$ch = curl_init($cod);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$codgruest = curl_exec($ch);
$codgruest = json_decode($codgruest, true);

$prod = 'http://localhost:8080/api/produto/' . $_GET['produto'];
$ch = curl_init($prod);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
$produto = json_decode($response, true);

?>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Alterar Produto</title>
    <link rel="stylesheet" href="CSS/crud.css">
</head>
<body>
<h1>Alterar Produto</h1>
<a href="adm.php">
    <button class="btnvolta">Voltar</button>
</a>
<form action="#" method="post" class="formaltera">
    <input type="hidden" name="id" value="<?php echo $produto['id'] ?>">
    <label for="descricao">Descrição:</label>
    <input type="text" name="descricao" id="descricao" value="<?php echo $produto['descricao'] ?>">
    <label for="preco">Preço:</label>
    <input type="text" name="preco" id="preco" value="<?php echo $produto['preco'] ?>">
    <label for="codGruEst">Código do Grupo:</label>
    <select name="codGruEst" id="codGruEst">
        <option value="">Selecione um código</option>

        <?php foreach ($codgruest as $cod): ?>
            <option value="<?php echo $cod['codigo'] ?>" <?php echo $produto['codGruEst'] == $cod['codigo'] ? 'selected' : '' ?>>
                <?php echo $cod['descricao'] ?>
            </option>
        <?php endforeach; ?>
    </select>
    <br>
    <button style="width: 10vw; height: 3vw" type="submit" class="btn-alterar">Alterar</button>
</form>
</body>
</html>