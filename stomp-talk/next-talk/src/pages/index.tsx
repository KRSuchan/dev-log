import React from "react";
import Header from "../components/Header";
import Link from "next/link";

const About: React.FC = () => (
    <div>
        <Header />
        <h2>Welcome to My Next.js App</h2>
        <footer>
            <ul>
                <li>
                    <Link href="/">Home</Link>
                </li>
                <li>
                    <Link href="/about">About</Link>
                </li>
                <li>
                    <Link href="/contact">Contact</Link>
                </li>
            </ul>
        </footer>
    </div>
);

export default About;
