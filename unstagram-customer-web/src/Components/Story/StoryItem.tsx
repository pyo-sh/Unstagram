import React from 'react';
import StoryItemBox from 'Styles/Story/StoryItemBox';

const StoryItem: React.FC = () => {
  return (
    <StoryItemBox>
        <button className="StoryItem-Button">
            <canvas className="StoryItem-Profile">
                <img/>
            </canvas>
        </button>
        <div className="StoryItem-Identification">
            아이디
        </div>
    </StoryItemBox>
  );
}

export default StoryItem;
