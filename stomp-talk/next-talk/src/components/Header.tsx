import React from "react";
import Link from "next/link";
import { CSSProperties } from "react";

const Header: React.FC = () => {
    return (
        <header>
            <h1>채팅방 선택</h1>
            <h2>Java</h2>
            <Link href="/stomp" style={styles.linkButton}>
                <button style={styles.button}>
                    STOMP 프로토콜을 이용한 기본 채팅방
                </button>
            </Link>
        </header>
    );
};

export default Header;

const styles: { [key: string]: CSSProperties } = {
    container: {
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
        gap: "20px",
        marginLeft: 10,
    },
    linkButton: {
        width: 300,
        height: 50,
        display: "inline-block",
        textDecoration: "none",
    },
    button: {
        width: "100%",
        height: "100%",
    },
};
