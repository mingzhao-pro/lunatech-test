angular.module('demo', [])
.controller('Demo', function($scope, $http) {

    $scope.triggered = false;

    $scope.clear = function() {
      $scope.airports = [];
       $scope.max = [];
       $scope.min = [];
       $scope.nbAirport;
       $scope.mostCommonRunways;
       $scope.triggered = false;
    }

    $scope.query = function() {
      $http.get('http://localhost:8080/query?country=' + $scope.country)
      .then(function(response) {
        $scope.clear();
        $scope.airports = response.data;
      });
    }

    $scope.report = function() {
       $scope.clear();
      $scope.triggered = true;
      $http.get('http://localhost:8080/report')
        .then(function(response){

              $scope.max = response.data[0];
              countries = "";
              angular.forEach(response.data[1], function(value, key) {
                countries += "[" + value.name + "] "
              })
              $scope.countries = countries;
              $scope.nbAirport = response.data[1][0].nbAirport;
        })

      $http.get('http://localhost:8080/mostPopularSurface')
              .then(function(response){
            idents = "";
            angular.forEach(response.data, function(value, key) {
                idents += value + " ";
            })

            $scope.mostCommonRunways = idents;
        })
    }
})
