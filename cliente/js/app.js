// Configuración de la aplicación AngularJS
var app = angular.module('rutasTuristicasApp', []);

// Constante para la URL base de la API
app.constant('API_URL', 'http://localhost:8080/api');

// Controlador principal
app.controller('RutasTuristicasController', function($scope, $http, API_URL) {
    
    // Variables principales
    $scope.ciudades = [];
    $scope.rutas = [];
    $scope.paradas = [];
    $scope.tipos = [];
    
    $scope.ciudadSeleccionada = null;
    $scope.rutaSeleccionada = null;
    
    $scope.modoEdicionRuta = false;
    $scope.modoEdicionParada = false;
    
    $scope.nuevaRuta = {};
    $scope.nuevaParada = {};
    
    // Inicialización
    $scope.init = function() {
        $scope.cargarCiudades();
        $scope.cargarTipos();
    };
    
    // ============ CIUDADES ============
    $scope.cargarCiudades = function() {
        $http.get(API_URL + '/ciudades')
            .then(function(response) {
                $scope.ciudades = response.data;
            })
            .catch(function(error) {
                console.error('Error al cargar ciudades:', error);
                alert('Error al cargar las ciudades');
            });
    };
    
    $scope.seleccionarCiudad = function(ciudad) {
        $scope.ciudadSeleccionada = ciudad;
        $scope.rutaSeleccionada = null;
        $scope.paradas = [];
        $scope.cargarRutasDeCiudad(ciudad.id);
    };
    
    // ============ RUTAS ============
    $scope.cargarRutasDeCiudad = function(idCiudad) {
        $http.get(API_URL + '/rutas/ciudad/' + idCiudad)
            .then(function(response) {
                $scope.rutas = response.data;
            })
            .catch(function(error) {
                console.error('Error al cargar rutas:', error);
                alert('Error al cargar las rutas de la ciudad');
            });
    };
    
    $scope.seleccionarRuta = function(ruta) {
        $scope.rutaSeleccionada = ruta;
        $scope.cargarParadasDeRuta(ruta.id);
    };
    
    $scope.mostrarFormularioRuta = function(ruta) {
        if (ruta) {
            // Modo edición
            $scope.modoEdicionRuta = true;
            $scope.nuevaRuta = angular.copy(ruta);
        } else {
            // Modo creación
            $scope.modoEdicionRuta = false;
            $scope.nuevaRuta = {
                nombre: '',
                descripcion: '',
                tipo: null,
                ciudad: $scope.ciudadSeleccionada
            };
        }
    };
    
    $scope.guardarRuta = function() {
        if (!$scope.nuevaRuta.nombre || !$scope.nuevaRuta.tipo) {
            alert('Por favor complete todos los campos obligatorios');
            return;
        }
        
        var metodo = $scope.modoEdicionRuta ? 'PUT' : 'POST';
        
        $http({
            method: metodo,
            url: API_URL + '/rutas',
            data: $scope.nuevaRuta
        })
        .then(function(response) {
            alert($scope.modoEdicionRuta ? 'Ruta actualizada exitosamente' : 'Ruta creada exitosamente');
            $scope.cargarRutasDeCiudad($scope.ciudadSeleccionada.id);
            $scope.cancelarFormularioRuta();
        })
        .catch(function(error) {
            console.error('Error al guardar ruta:', error);
            alert('Error al guardar la ruta');
        });
    };
    
    $scope.eliminarRuta = function(ruta) {
        if (!confirm('¿Está seguro de eliminar la ruta "' + ruta.nombre + '"?')) {
            return;
        }
        
        $http.delete(API_URL + '/rutas/' + ruta.id)
            .then(function(response) {
                alert('Ruta eliminada exitosamente');
                $scope.cargarRutasDeCiudad($scope.ciudadSeleccionada.id);
                if ($scope.rutaSeleccionada && $scope.rutaSeleccionada.id === ruta.id) {
                    $scope.rutaSeleccionada = null;
                    $scope.paradas = [];
                }
            })
            .catch(function(error) {
                console.error('Error al eliminar ruta:', error);
                alert('Error al eliminar la ruta');
            });
    };
    
    $scope.cancelarFormularioRuta = function() {
        $scope.nuevaRuta = {};
        $scope.modoEdicionRuta = false;
    };
    
    // ============ PARADAS ============
    $scope.cargarParadasDeRuta = function(idRuta) {
        $http.get(API_URL + '/paradas/ruta/' + idRuta)
            .then(function(response) {
                $scope.paradas = response.data;
            })
            .catch(function(error) {
                console.error('Error al cargar paradas:', error);
                alert('Error al cargar las paradas de la ruta');
            });
    };
    
    $scope.mostrarFormularioParada = function(parada) {
        if (parada) {
            // Modo edición
            $scope.modoEdicionParada = true;
            $scope.nuevaParada = angular.copy(parada);
        } else {
            // Modo creación
            $scope.modoEdicionParada = false;
            $scope.nuevaParada = {
                nombre: '',
                orden: $scope.paradas.length + 1,
                longitud: null,
                latitud: null,
                tiempo: null,
                descripcion: '',
                ruta: $scope.rutaSeleccionada
            };
        }
    };
    
    $scope.guardarParada = function() {
        if (!$scope.nuevaParada.nombre || !$scope.nuevaParada.orden || 
            !$scope.nuevaParada.longitud || !$scope.nuevaParada.latitud || 
            !$scope.nuevaParada.tiempo) {
            alert('Por favor complete todos los campos obligatorios');
            return;
        }
        
        var metodo = $scope.modoEdicionParada ? 'PUT' : 'POST';
        
        $http({
            method: metodo,
            url: API_URL + '/paradas',
            data: $scope.nuevaParada
        })
        .then(function(response) {
            alert($scope.modoEdicionParada ? 'Parada actualizada exitosamente' : 'Parada creada exitosamente');
            $scope.cargarParadasDeRuta($scope.rutaSeleccionada.id);
            $scope.cancelarFormularioParada();
        })
        .catch(function(error) {
            console.error('Error al guardar parada:', error);
            alert('Error al guardar la parada');
        });
    };
    
    $scope.eliminarParada = function(parada) {
        if (!confirm('¿Está seguro de eliminar la parada "' + parada.nombre + '"?')) {
            return;
        }
        
        $http.delete(API_URL + '/paradas/' + parada.id)
            .then(function(response) {
                alert('Parada eliminada exitosamente');
                $scope.cargarParadasDeRuta($scope.rutaSeleccionada.id);
            })
            .catch(function(error) {
                console.error('Error al eliminar parada:', error);
                alert('Error al eliminar la parada');
            });
    };
    
    $scope.cancelarFormularioParada = function() {
        $scope.nuevaParada = {};
        $scope.modoEdicionParada = false;
    };
    
    // ============ TIPOS ============
    $scope.cargarTipos = function() {
        $http.get(API_URL + '/tipos')
            .then(function(response) {
                $scope.tipos = response.data;
            })
            .catch(function(error) {
                console.error('Error al cargar tipos:', error);
            });
    };
    
    // Inicializar aplicación
    $scope.init();
});
