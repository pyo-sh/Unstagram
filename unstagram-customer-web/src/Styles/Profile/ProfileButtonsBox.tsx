import styled from 'styled-components';

export interface IconProp{
    isMe: boolean,
    className: string,
    fill: string,
    width: number,
    height: number,
    viewBox: string
}
"#262626"
"24"
"24"
"0 0 48 48"
export const buttonIcon: Function = (props: IconProp): JSX.Element => {
    const { isMe, className, fill, width, height, viewBox } = props;

    return isMe
        ? (
            <svg aria-label="Option" className={className} fill={fill} width={width} height={height} viewBox={viewBox}>
                <path clipRule="evenodd" d="M46.7 20.6l-2.1-1.1c-.4-.2-.7-.5-.8-1-.5-1.6-1.1-3.2-1.9-4.7-.2-.4-.3-.8-.1-1.2l.8-2.3c.2-.5 0-1.1-.4-1.5l-2.9-2.9c-.4-.4-1-.5-1.5-.4l-2.3.8c-.4.1-.8.1-1.2-.1-1.4-.8-3-1.5-4.6-1.9-.4-.1-.8-.4-1-.8l-1.1-2.2c-.3-.5-.8-.8-1.3-.8h-4.1c-.6 0-1.1.3-1.3.8l-1.1 2.2c-.2.4-.5.7-1 .8-1.6.5-3.2 1.1-4.6 1.9-.4.2-.8.3-1.2.1l-2.3-.8c-.5-.2-1.1 0-1.5.4L5.9 8.8c-.4.4-.5 1-.4 1.5l.8 2.3c.1.4.1.8-.1 1.2-.8 1.5-1.5 3-1.9 4.7-.1.4-.4.8-.8 1l-2.1 1.1c-.5.3-.8.8-.8 1.3V26c0 .6.3 1.1.8 1.3l2.1 1.1c.4.2.7.5.8 1 .5 1.6 1.1 3.2 1.9 4.7.2.4.3.8.1 1.2l-.8 2.3c-.2.5 0 1.1.4 1.5L8.8 42c.4.4 1 .5 1.5.4l2.3-.8c.4-.1.8-.1 1.2.1 1.4.8 3 1.5 4.6 1.9.4.1.8.4 1 .8l1.1 2.2c.3.5.8.8 1.3.8h4.1c.6 0 1.1-.3 1.3-.8l1.1-2.2c.2-.4.5-.7 1-.8 1.6-.5 3.2-1.1 4.6-1.9.4-.2.8-.3 1.2-.1l2.3.8c.5.2 1.1 0 1.5-.4l2.9-2.9c.4-.4.5-1 .4-1.5l-.8-2.3c-.1-.4-.1-.8.1-1.2.8-1.5 1.5-3 1.9-4.7.1-.4.4-.8.8-1l2.1-1.1c.5-.3.8-.8.8-1.3v-4.1c.4-.5.1-1.1-.4-1.3zM24 41.5c-9.7 0-17.5-7.8-17.5-17.5S14.3 6.5 24 6.5 41.5 14.3 41.5 24 33.7 41.5 24 41.5z" fillRule="evenodd">
                </path>
            </svg>
        )
        : (
            <svg aria-label="Option" className={className} fill={fill} width={width} height={height} viewBox={viewBox}>
                <circle clipRule="evenodd" cx="8" cy="24" fillRule="evenodd" r="4.5">
                </circle>
                <circle clipRule="evenodd" cx="24" cy="24" fillRule="evenodd" r="4.5">
                </circle>
                <circle clipRule="evenodd" cx="40" cy="24" fillRule="evenodd" r="4.5">
                </circle>
            </svg>
        );
}

const ProfileButtonsBox = styled.div`
    align-items: center;

    .ProfileButtons-Identification{
        margin: 0;
        height: 32px;

        display: block;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;

        font-size: 28px;
        font-weight: 300;
        color: rgba(var(--i1d,38,38,38),1);
    }

    .ProfileButtons-Functions{
        height: 30px;
        margin: 0 0 0 20px;

        justify-content: center;
        align-items: center;

        .ProfileButtons-Functions-Button{
            height: 100%;
            margin-right: 8px;
            
            cursor: pointer;
            font-size: 14px;
            font-weight: 600;
            text-align: center;
            text-transform: inherit;
            text-overflow: ellipsis;

            display: block;
            background-color: transparent;
            border: 1px solid rgba(var(--ca6,219,219,219),1);
            border-radius: 3px;
            color: rgba(var(--f75,38,38,38),1);

            :focus {
                outline: none;
            }
        }

        .ProfileButtons-Functions-Default{
            padding: 5px 9px;
        }

        .ProfileButtons-Functions-Button-Following{
            padding: 0 24px;
            color:#8b8b8b;
            justify-content: center;
            align-items: center;
            font-size: 14px;
            font-weight: 300;
            line-height: 12px;
        }

        .ProfileButtons-Functions-Image{
            width: 12px;
            height: 12px;
        }
        
        .ProfileButtons-Functions-Button-Menu{
            padding: 0 12px;
            margin: 0;
        }
        .ProfileButtons-Functions-Menu{
            margin: 0;
            width: 0;
            height: 0;
            border-top: 6px solid black;
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
        }

        .ProfileButtons-Functions-Button-Follow{
            padding: 0 24px;
            background: rgba(var(--d69,0,149,246),1);
            border-color: rgba(var(--d69,0,149,246),1);
            color: rgba(var(--eca,255,255,255),1);
        }
        .ProfileButtons-Functions-Button-Menu2{
            padding: 0 12px;
            margin: 0;
            background: rgba(var(--d69,0,149,246),1);
            border-color: rgba(var(--d69,0,149,246),1);
        }
        .ProfileButtons-Functions-Menu2{
            margin: 0;
            width: 0;
            height: 0;
            border-top: 6px solid white;
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
        }
        
    }

    .ProfileButtons-Setting{
        width: 40px;
        height: 40px;
        padding: 8px;
        margin: 0;

        background: none;
        border: none;
        outline: none;
    }

    @media(min-width: 736px){
        margin: 0 0 20px 0;
    }
    @media(max-width: 735px){
        margin: 0 0 12px 0;
        min-width: 260px;

        flex-wrap: wrap;
        .ProfileButtons-Functions{
            order: 1;
        }
        
        .ProfileButtons-Setting{
            margin-right: auto;
        }
    }
`;

export default ProfileButtonsBox;