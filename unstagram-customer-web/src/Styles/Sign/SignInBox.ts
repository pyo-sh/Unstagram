import styled from 'styled-components';

const SignInBox = styled.div`
    width: 350px;
    height: 380px;
    padding: 10px 0;
    margin: 0 0 10px;

    flex-direction: column;
    align-items: center;

    background-color: rgba(var(--d87,255,255,255),1);
    border: 1px solid rgba(var(--b6a,219,219,219),1);

    .SignIn-Title{
        height: 51px;
        width: 175px;
        margin: 22px auto 12px;

        font-size: 42px;
    }

    .SignIn-Form{
        width: 100%;
        margin: 24px 0 0;

        flex-direction: column;
        align-items: center;

        .SignIn-Form-Input{
            width: 268px;
            height: 36px;

            padding: 9px 0px 7px 8px;
            margin: 0 0 8px;

            background: rgba(var(--b3f,250,250,250),1);
            outline: 0;
            overflow: hidden;
            text-overflow: ellipsis;

            color: rgba(var(--i1d,38,38,38),1);
            font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Helvetica,Arial,sans-serif;
            font-size: 12px;
            line-height: 18px;

            border: 1px solid #dbdbdb;
            border-radius: 3px;
        }
        .SignIn-Form-Input :focus{
            border: 1px solid #a8a8a8;
        }
        .SignIn-Form-Button{
            width: 268px;
            height: 30px;
            margin: 8px 40px;

            justify-content: center;
            align-items: center;

            font-weight: bold;
            font-size: 14px;
            border: none;
            border-radius: 3px;
            background-color: #0095f6;
            color: #ffffff;
        }
    }

    .SignIn-Seperate{
        width: 268px;
        height: 15px;

        margin: 10px 40px 18px;
        .SignIn-Seperate-Line{
            height: 1px;
            position: relative;
            top: .45em;

            flex-grow: 1;
            flex-shrink: 1;
            background-color: rgba(var(--b38,219,219,219),1);
        }
        .SignIn-Seperate-Title{
            width: 26px;
            height: 15px;
            margin: 0 18px;

            font-size: 13px;
            font-weight: bold;
            color: rgba(var(--f52,142,142,142),1);
        }
    }

    .SignIn-Password-Reset{
        color: rgba(var(--fe0,0,55,107),1);
        font-size: 12px;
        line-height: 14px;
        margin-top: 12px;
        text-align: center;
    }
`;

export default SignInBox;