# Malls:

The Malls application utilises the citylibrary data on Cities , Malls and Shops.

#citylibrary:

The citylibrary provides City data fetched for the Server.
ofline data available for a max of 7 days.

to get Cities,Malls and their shops you can use the CityLibrary class.

to instantiate CityLibrary class you'll need contex, like so:

// CityLibrary library = new CityLibrary(context);
// library.syncCityData();

NB: before requesting data you need to Synch the libra as indicated above.

then you can retrieve your data using the CityLibrary instance.

//List<City> cities = library.getCities();


