<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/stylmod.css" rel="stylesheet">
<title>Inserimento studente</title>
</head>
<body>
	<div id="exform" class="container-fluid ex">
		<h2>Inserimento di un nuovo studente</h2>
		<div class="col-md-4 col-md-offset-4">
			<div class="form_container">
				<form action="controller" method="post">
					<div class="form-group">
						<p>
							${matricolaError}
						</p>
						<label>Matricola</label> <input type="text" class="form-control"
							placeholder="Matricola" name="matricola"
							value='${param["matricola"]}'/>
					</div>
					<div class="form-group">
						<p>
							${nomeError}
						</p>
						<label>Nome</label> <input type="text" class="form-control"
							placeholder="Nome" name="nome"
							value='${param["nome"]}'/>
					</div>
					<div class="form-group">
						<p>
							${cognomeError}
						</p>
						<label>Cognome</label><input type="text" class="form-control"
							placeholder="Cognome" name="cognome"
							value='${param["cognome"]}'>
					</div>
										<div class="form-group">
						<p>
							${dataDiNascitaError}
						</p>
						<label>Data di nascita: </label><input type="date" class="form-control"
							placeholder="Data di nascita" name="dataDiNascita"
							value='${param["dataDiNascita"]}'>
					</div>
					<button type="submit" class="btn btn-default">Invia</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>