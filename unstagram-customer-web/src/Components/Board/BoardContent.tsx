import React from 'react';
import BoardContentBox from 'Styles/Board/BoardContentBox';

interface PropType {
  content: string
}

const BoardContent: React.FC<PropType> = ({ content }) => {
  return (
    <BoardContentBox>
        <div className="BoardContent-Likes">좋아요 {0}개</div>
        <div className="BoardContent-Content">
          <a className="BoardContent-Author">identification</a>
          {content}
        </div>
        <div className="BoardContent-Date">{10}시간 전</div>
    </BoardContentBox>
  );
}

export default BoardContent;
