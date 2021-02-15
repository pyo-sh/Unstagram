import React from 'react';
import SignIn from 'Components/Sign/SignIn';
import StoryList from 'Components/Story/StoryList';
import Header from 'Components/Main/Header';

const App: React.FC = () => {
  return (
    <div className="App">
      <Header/>
      <StoryList></StoryList>
    </div>
  );
}

export default App;
