import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  * {
    display: flex;
    box-sizing: border-box;
  }
  head{
    display:none;
  }
  body{
    margin: 0;
    padding: 0;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
      'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
      sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  script{
    display: none;
  }
  .Unstagram-Title{
    font-family: 'Dancing Script', cursive;

    // 드래그 못하게
    -ms-user-select: none; 
    -moz-user-select: -moz-none;
    -khtml-user-select: none;
    -webkit-user-select: none;
    user-select: none;
  }
  .App{
    width: 100vw;
    flex-direction: column;
    justify-content:center;
    align-items:center;
  }
`;

export default GlobalStyle;