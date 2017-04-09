

function initAutocomplete() {

    // inputs.forEach(function(input) {
    doGoogleMaps('pac-input');
    doGoogleMaps('pac-input2');

}

function doGoogleMaps(className) {
    var input = document.getElementById(className);
    var searchBox = new google.maps.places.SearchBox(input);
    // map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    // map.addListener('bounds_changed', function() {
    //     searchBox.setBounds(map.getBounds());
    // });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
        var places = searchBox.getPlaces();
        var finalPlace;
        var num = 0;
        places.forEach(function(place) {
            finalPlace = place;
            num++;
        });
        $(function () {
            $('#' + className).val(finalPlace.name);
        });
    });
}