<?php
session_start();

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, "http://localhost:8080/api/produto");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
curl_close($ch);

$produtos = json_decode($response, true);

$produtosPorCodGruEst = [];

foreach ($produtos as $produto) {
    $codGruEst = $produto['codGruEst'];
    if (!isset($produtosPorCodGruEst[$codGruEst])) {
        $produtosPorCodGruEst[$codGruEst] = [];
    }
    $produtosPorCodGruEst[$codGruEst][] = $produto;
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/crud.css">
    <title>CRUD de Produtos</title>
</head>
<body>
<h1>CRUD de Produtos</h1>
<a href="index.php"><button class="btnvolta">Voltar</button></a>
<?php foreach ($produtosPorCodGruEst as $codGruEst => $produtos): ?>
    <h2>Grupo: <?php echo htmlspecialchars($codGruEst); ?></h2>
    <table class="tablecodgruest">
        <thead>
        <tr>
            <th>Nome do Produto</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <?php foreach ($produtos as $produto): ?>
            <tr style="margin: 10px 0 10px">
                <td><?php echo htmlspecialchars($produto['descricao']); ?></td>
                <td style="text-align: center"><?php echo number_format($produto['preco'], 2, ',', '.'); ?></td>
                <td style="display: flex; flex-direction: row; justify-content: center">
                    <form action="deletaproduto.php" method="post">
                        <input type="hidden" name="id" value="<?php echo $produto['id'] ?>">
                        <button class="btn-excluir" type="submit">Excluir
                        </button>
                    </form>
                    <a href="altera.php?produto=<?php echo $produto['id'] ?>" style="text-decoration: none"><button class="btn-alterar">Alterar</button></a>
                </td>
            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
<?php endforeach; ?>
</body>
</html>