package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // 추상화에만 의존하도록 수정 됨, 생성자를 통한 객체 주입
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
