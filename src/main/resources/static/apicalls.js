//TODO Load URLs and keys from external file
const googleBooksApiKey = "AIzaSyAOsteuaW5ifVvA_RkLXh0mYs6GLAD6ykc";
const omdbApiKey = "e39217d";
const lotrApiKey = "mjdxc8VTUmJ9MOHlbH4M";

const randomChuckNorrisJokesUrl = "https://geek-jokes.sameerkumar.website/api?format=json";
const randomRegularJokesUrl = "https://icanhazdadjoke.com";
const randomDevJokesUrl = "https://backend-omega-seven.vercel.app/api/getjoke";
const randomFriendsUrl = "https://friends-quotes-api.herokuapp.com/quotes/random";
const inspirationalQuotesCollectionUrl = "https://type.fit/api/quotes";
const movieAndTVQuotesCollection = "resources/movie-quotes.json";
const movieCollectionUrl = "https://omdbapi.com"
const harryPotterCharactersUrl = "http://hp-api.herokuapp.com/api/characters";
const lotrCharactersUrl = "https://the-one-api.dev/v2/character";
const starwarsUrl = "https://swapi.dev/api/people";
const googleBooksUrl = "https://www.googleapis.com/books/v1/volumes";
const countriesCollection = "resources/countries.json";

async function fetchData(url, domElement) {
    try {
        const getData = await fetch(url, {
            headers: {
                Accept: "application/json"
            }
        });
        const object = await getData.json();
        console.log(object);
    } catch (error) {
        console.log(error);
    }
}

async function fetchBooks(domElement, titleSearch, authorSearch) {
    try {
        const getData = await fetch(`${googleBooksUrl}?q=${titleSearch}+inauthor:${authorSearch}&key=${googleBooksApiKey}`)
        const object = await getData.json();
        console.log(object);
    } catch (error) {
        console.log(error);
    }
}

async function fetchMovies(domElement, titleSearch) {
    try {
        const getData = await fetch(`${movieCollectionUrl}/?s=${titleSearch}&apikey=${omdbApiKey}`);
        const object = await getData.json();
        console.log(object);
    } catch (error) {
        console.log(error);
    }
}

async function fetchLotrCharacters(domElement) {
    try {
        const getData = await fetch(lotrCharactersUrl, {
            headers: {
                Authorization: `Bearer ${lotrApiKey}`
            }
        });
        const object = await getData.json()
        console.log(object);
    } catch (error) {
        console.log(error);
    }
}

async function fetchLotrCharacters(domElement) {
    try {
        const getData = await fetch(lotrCharactersUrl, {
            headers: {
                Authorization: `Bearer ${lotrApiKey}`
            }
        });
        const object = await getData.json()
        console.log(object);
    } catch (error) {
        console.log(error);
    }
}

function assignValues() {
    fetchData(randomChuckNorrisJokesUrl);
    fetchData(randomRegularJokesUrl);
    fetchData(randomDevJokesUrl);
    fetchData(randomFriendsUrl);
    fetchBooks('...','flowers', 'keyes');
    fetchMovies(".", "titanic");
    fetchData(harryPotterCharactersUrl);
    fetchLotrCharacters("..");
    fetchData(starwarsUrl);
    fetchData(countriesCollection);

}


assignValues();

