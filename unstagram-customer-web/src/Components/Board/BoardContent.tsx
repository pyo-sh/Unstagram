import React from 'react';
import BoardContentBox from 'Styles/Board/BoardContentBox';

const BoardContent: React.FC = () => {
  return (
    <BoardContentBox>
        <div className="BoardContent-Likes">좋아요 {0}개</div>
        <div className="BoardContent-Content">
          <a className="BoardContent-Author">identification</a>
          &nbsp;
          이건 더 말해야 할 것...<br/>d?움ㄴ와오ㅘ와ㅗ아아ㅗ아오ㅘ오아ㅗ아와ㅗ아ㅗ아ㅗ아ㅘ오아아ㅗㅇ
          아아
          ㅗ아ㅗ
          아ㅗ아ㅗ
          오
          아ㅗ
          아
          ㅗㅇ
          ㅏㅗㅇ
          
        </div>
        <div className="BoardContent-Date">{10}시간 전</div>
    </BoardContentBox>
  );
}

export default BoardContent;
