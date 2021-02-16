import styled from 'styled-components';

export const imojiIcon: JSX.Element = <svg aria-label="이모티콘" fill="#262626" width="24" height="24" viewBox="0 0 48 48">
    <path d="M24 48C10.8 48 0 37.2 0 24S10.8 0 24 0s24 10.8 24 24-10.8 24-24 24zm0-45C12.4 3 3 12.4 3 24s9.4 21 21 21 21-9.4 21-21S35.6 3 24 3z">
    </path>
    <path d="M34.9 24c0-1.4-1.1-2.5-2.5-2.5s-2.5 1.1-2.5 2.5 1.1 2.5 2.5 2.5 2.5-1.1 2.5-2.5zm-21.8 0c0-1.4 1.1-2.5 2.5-2.5s2.5 1.1 2.5 2.5-1.1 2.5-2.5 2.5-2.5-1.1-2.5-2.5zM24 37.3c-5.2 0-8-3.5-8.2-3.7-.5-.6-.4-1.6.2-2.1.6-.5 1.6-.4 2.1.2.1.1 2.1 2.5 5.8 2.5 3.7 0 5.8-2.5 5.8-2.5.5-.6 1.5-.7 2.1-.2.6.5.7 1.5.2 2.1 0 .2-2.8 3.7-8 3.7z">
    </path>
</svg>;

const BoardCommentBox = styled.section`
    width: 100%;
    height: auto;
    min-height: 56px;
    max-height: 80px;
    margin-top: 4px;
    padding: 0 16px;

    align-items: center;

    border-top: 1px solid rgba(var(--ce3,239,239,239),1);

    .BoardComment-form{
        width: 100%;
        flex-shrink: 1;

        align-items: center;

        .BoardComment-Imoji{
            width: 40px;
            height: 40px;
            padding: 8px 16px 8px 0px;
            
            background: none;
            border:none;
            cursor: pointer;

            :focus{
                outline: none;
            }
        }
        .BoardComment-Input{
            height: 19px;
            max-height: 80px;
            padding: 0;
            resize: none;

            flex-grow: 1;
            font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Helvetica,Arial,sans-serif;
            font-size: 14px;
            outline: none;

            background: none;
            color: rgba(var(--i1d,38,38,38),1);

            border: none;
            cursor: text;
            white-space: pre-wrap;
            overflow: visible;
        }
        .BoardComment-Submit{
            width: 28px;
            max-height: 80px;
            padding: 0;

            color: rgba(var(--d69,0,149,246),1);

            font-size: 14px;
            font-weight: 600;
            background: none;
            border: none;
            cursor: pointer;

            :focus{
                outline: none;
            }
        }
    }
`;

export default BoardCommentBox;