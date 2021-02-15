import React from 'react';
import SignIn from 'Components/Sign/SignIn';
import Header from 'Components/Main/Header';

const App: React.FC = () => {
  return (
    <div className="App">
      <Header/>
      <SignIn></SignIn>
    </div>
  );
}

export default App;
