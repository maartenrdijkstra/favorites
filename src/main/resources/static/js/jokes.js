// const jokeField = document.getElementById("jokeField");
//
// fetchDevJoke();
// const jokeField = document.getElementById("jokeField");
//
// async function fetchDevJoke() {
//     try {
//         const getData = await fetch("https://backend-omega-seven.vercel.app/api/getjoke", {
//             headers: {
//                 Accept: "application/json"
//             }
//         });
//         const object = await getData.json();
//         const question = object[0].question;
//         const punchline = object[0].punchline;
//
//         jokeField.innerText = (`${question}\n${punchline}`);
//
//     } catch (error) {
//         console.log(error);
//     }
// }
//
