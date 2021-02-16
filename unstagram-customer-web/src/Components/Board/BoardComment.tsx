import React, { useState, useRef } from 'react';
import BoardCommentBox,  { imojiIcon } from 'Styles/Board/BoardCommentBox';

const BoardComment: React.FC = () => {

    const onChange = (event: React.ChangeEvent<HTMLTextAreaElement>): void => {
        event.target.style.setProperty('height', '1px');
        event.target.style.setProperty('height', event.target.scrollHeight + 'px');
    }

    return (
        <BoardCommentBox>
            <form className="BoardComment-form">
                <button className="BoardComment-Imoji">
                    {imojiIcon}
                </button>
                <textarea
                    className="BoardComment-Input"
                    placeholder="댓글 달기..."
                    onChange={onChange}
                    />
                <button className="BoardComment-Submit">게시</button>
            </form>
        </BoardCommentBox>
    );
}

export default BoardComment;