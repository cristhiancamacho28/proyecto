$(document).ready(function(){
	cargarDataTable();
});

var dataTable;
function cargarDataTable() {
	console.log("sadasd");
	dataTable = $('#tablaCursos').DataTable({
		"ajax": {
			"type": "GET",
			"datatype": "json",
			"url": "/cursos/api/listadoCursos"
		},
		"columns": [
			{ "data": "id", "width": "5%" },
			{ "data": "nombre", "width": "5%" },
			{ "data": "horasDuracion", "width": "5%" },
			{ "data": "categoria.nombre", "width": "5%" },
			{
				"data": "id",
				"width": "20%",
				"render": function(data, type, row, meta){
					return row.instructor.nombre + ' ' + row.instructor.apPaterno + ' '
					    + row.instructor.apMaterno
				}
			},
			{"data": "temario.numTemas", "width": "10%"},													
			{
				"data": "id",
				"width": "30%",
				"render": function(data){
				return `<div class="text-center"><a class="btn btn-primary"
				href="/cursos/edit/${data}">Editar</a>&nbsp;
				<a class="btn btn-danger text=white" style="cursor:pointer;"
				onclick="Delete('/cursos/api/delete/${data}')">Eliminar</a>
				</div>`
			}
		}],
		"language": {
			"decimal": "",
			"emptyTable": "No hay información",
			"info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
			"infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
			"infoFiltered": "(Filtrado de _MAX_ total entradas)",
			"infoPostFix": "",
			"thousands": ",",
			"lengthMenu": "Mostrar _MENU_ Entradas",
			"loadingRecords": "Cargando...",
			"processing": "Procesando...",
			"search": "Buscar:",
			"zeroRecords": "Sin resultados encontrados",
			"paginate": {
				"first": "Primero",
				"last": "Ultimo",
				"next": "Siguiente",
				"previous": "Anterior"
			}
		}
	});
}

function Delete(url){
	swal({
		title: "Esta seguro de borar?",
		text: "Este contenido no se puede recuperar",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "Si, borrar!",
		closeOnconfirm: true		
	}, function(){
		$.ajax({
			type: 'DELETE',
			url: url,  //"/cursos/api/delete/"
			success: function(response){
				if (response.success == "true") {
					toastr.success(response.message);
					dataTable.ajax.reload();
				}
				else{
					toastr.error(data.message);
				}
			}
		});
	});
}