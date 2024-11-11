<?php

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}

if (!$_COOKIE['role']) {
    header('Location: index.php');
    exit();
}

$cod = 'http://localhost:8080/api/codgruest';
$ch = curl_init($cod);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$codgruest = curl_exec($ch);
$codgruest = json_decode($codgruest, true);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $role = 'http://localhost:8080/api/produto/cod/' . $_POST['codigoProduto'];
    $bah = file_get_contents($role);
    if ($bah == $_POST['codigoProduto']) {
        $_SESSION['error'] = 'Código de produto já existe';
    } else {
        $data = [
            [
                'codigoProduto' => $_POST['codigoProduto'],
                'descricao' => $_POST['descricao'],
                'preco' => $_POST['preco'],
                'codGruEst' => $_POST['codGruEst']
            ]
        ];

        print_r($data);

        $ch = curl_init('http://localhost:8080/api/produto');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Authorization: Bearer ' . $_COOKIE['token']
        ]);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        $response = curl_exec($ch);
        curl_close($ch);
        $response = json_decode($response, true);
        header('Location: adm.php');
        exit();
    }
}

?>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Criar Produto</title>
    <link rel="stylesheet" href="CSS/crud.css">
</head>
<body>
<h1>Criar Produto</h1>
<a href="adm.php">
    <button class="btnvolta">Voltar</button>
</a>

<?php
if (isset($_SESSION['error'])) {
    echo '<div class="erro">';
    echo $_SESSION['error'];
    unset($_SESSION['error']);
    echo "</div>";
}
?>
<form method="post" action="#" class="formaltera">
    <label for="codigoProduto">Código do Produto</label>
    <input type="number" id="codigoProduto" name="codigoProduto" required>
    <label for="descricao">Descrição</label>
    <input type="text" id="descricao" name="descricao" required>
    <label for="preco">Preço</label>
    <input type="number" id="preco" name="preco" step="0.01" required>
    <label for="codGruEst">Código do Grupo</label>
    <select id="codGruEst" name="codGruEst" required>
        <?php foreach ($codgruest as $cod): ?>
            <option value="<?php echo $cod['codigo']; ?>"><?php echo $cod['descricao']; ?></option>
        <?php endforeach; ?>
    </select>
    <button type="submit" class="btnvolta" style="background-color: #009900; margin-top: 10px" >Criar</button>
</form>
</body>
</html>

