import React from 'react';
import StoryListBox from 'Styles/Story/StoryListBox';
import StoryItem from './StoryItem';

const StoryList: React.FC = () => {
  return (
    <StoryListBox>
      <StoryItem></StoryItem>
    </StoryListBox>
  );
}

export default StoryList;
